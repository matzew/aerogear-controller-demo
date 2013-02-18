/*! AeroGear JavaScript Library - v1.0.0.Alpha - 2012-08-22
* https://github.com/aerogear/aerogear-js
* JBoss, Home of Professional Open Source
* Copyright 2012, Red Hat, Inc., and individual contributors
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
* http://www.apache.org/licenses/LICENSE-2.0
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/

(function( window, undefined ) {
    /**
     * aerogear
     *
     * The aerogear namespace provides a way to encapsulate the library's properties and methods away from the global namespace
    **/
    var aerogear = window.aerogear = {};
})( this );

(function( aerogear, undefined ) {
    function isArray( obj ) {
        return ({}).toString.call( obj ) === "[object Array]";
    }

    /**
     * aerogear.pipeline
     *
     * The aerogear.pipeline namespace provides a persistence API that is protocol agnostic and does not depend on any certain data model. Through the use of adapters, both provided and custom, user supplied, this library provides common methods like read, save and delete that will just work.
     *
     * `aerogear.pipeline( pipe ) -> Object`
     * - **pipe** (Mixed) When passing a pipeConfiguration object to `add`, the following items can be provided:
     *  - **name** - String (Required), the name that the pipe will later be referenced by
     *  - **type** - String (Optional, default - "rest"), the type of pipe as determined by the adapter used
     *  - **recordId** - String (Optional, default - "id"), the identifier used to denote the unique id for each record in the data associated with this pipe
     *  - **settings** - Object (Optional, default - {}), the settings to be passed to the adapter
     *   - Adapters may have a number of varying configuration settings.
     *
     * Returns an object representing a collection of server connections (pipes) and their corresponding data models. This object provides a standard way to communicate with the server no matter the data format or transport expected.
     *
     * ##### Example
     *
     *     // Create a single pipe using the default adapter
     *     var pipeline = aerogear.pipeline( "tasks" );
     *
     *     // Create multiple pipes using the default adapter
     *     var myPipeline = aerogear.pipeline( [ "tasks", "projects" ] );
     **/
    aerogear.pipeline = function( pipe ) {
        var pipeline = {
                pipes: {},
                /**
                 * aerogear.pipeline#add( pipe ) -> Object
                 * - pipe (Mixed): This can be a variety of types specifying how to create the pipe as illustrated below
                 *
                 * When passing a pipeConfiguration object to `add`, the following items can be provided:
                 *  - **name** - String (Required), the name that the pipe will later be referenced by
                 *  - **type** - String (Optional, default - "rest"), the type of pipe as determined by the adapter used
                 *  - **recordId** - String (Optional, default - "id"), the identifier used to denote the unique id for each record in the data associated with this pipe
                 *  - **settings** - Object (Optional, default - {}), the settings to be passed to the adapter
                 *   - Adapters may have a number of varying configuration settings.
                 *
                 * Returns the full pipeline object with the new pipe(s) added
                 *
                 *     // Add a single pipe using the default configuration (rest).
                 *     aerogear.pipeline.add( String pipeName );
                 *
                 *     // Add multiple pipes all using the default configuration (rest).
                 *     aerogear.pipeline.add( Array[String] pipeNames );
                 *
                 *     // Add one or more pipe configuration objects.
                 *     aerogear.pipeline.add( Object/Array[Object] pipeConfigurations )
                 *
                 * The default pipe type is `rest`. You may also use one of the other provided types or create your own.
                 *
                 * ##### Example
                 *
                 *     var pipeline = aerogear.pipeline();
                 *
                 *     // Add a single pipe using the default adapter
                 *     pipeline = pipeline.add( "tasks" );
                 *
                 *     // Add multiple pipes using the default adapter
                 *     pipeline = pipeline.add( [ "tags", "projects" ] );
                 *
                 **/
                add: function( pipe ) {
                    var i,
                        current;

                    if ( !pipe ) {
                        return this;
                    } else if ( typeof pipe === "string" ) {
                        // pipe is a string so use
                        this.pipes[ pipe ] = aerogear.pipeline.adapters.rest( pipe, "id" );
                    } else if ( isArray( pipe ) ) {
                        // pipe is an array so loop through each item in the array
                        for ( i = 0; i < pipe.length; i++ ) {
                            current = pipe[ i ];

                            if ( typeof current === "string" ) {
                                this.pipes[ current ] = aerogear.pipeline.adapters.rest( current );
                            } else {
                                this.pipes[ current.name ] = aerogear.pipeline.adapters[ current.type || "rest" ]( current.name, current.recordId || "id", current.settings || {} );
                            }
                        }
                    } else {
                        // pipe is an object so use that signature
                        this.pipes[ pipe.name ] = aerogear.pipeline.adapters[ pipe.type || "rest" ]( pipe.name, pipe.recordId || "id", pipe.settings || {} );
                    }

                    return this;
                },
                /**
                 * aerogear.pipeline#remove( pipe ) -> Object
                 * - pipe (Mixed): This can be a variety of types specifying the pipe to remove as illustrated below
                 *
                 * When passing a pipeConfiguration object to `remove`, the following items can be provided:
                 *  - **name** - String (Required), the name that the pipe will later be referenced by
                 *  - **type** - String (Optional, default - "rest"), the type of pipe as determined by the adapter used
                 *  - **recordId** - String (Optional, default - "id"), the identifier used to denote the unique id for each record in the data associated with this pipe
                 *  - **settings** - Object (Optional, default - {}), the settings to be passed to the adapter
                 *   - Adapters may have a number of varying configuration settings.
                 *
                 * Returns the full pipeline object with the specified pipe(s) removed
                 *
                 *     // Remove a single pipe using the default configuration (rest).
                 *     aerogear.pipeline.remove( String pipeName );
                 *
                 *     // Remove multiple pipes all using the default configuration (rest).
                 *     aerogear.pipeline.remove( Array[String] pipeNames );
                 *
                 *     // Remove one or more pipe configuration objects.
                 *     aerogear.pipeline.remove( Object/Array[Object] pipeConfigurations )
                 *
                 * ##### Example
                 *
                 *     var pipeline = aerogear.pipeline( [ "projects", "tags", "tasks" ] );
                 *
                 *     // Remove a single pipe
                 *     pipeline.remove( "tasks" );
                 *
                 *     // Remove multiple pipes using the default adapter
                 *     pipeline.remove( [ "tags", "projects" ] );
                 *
                 **/
                remove: function( pipe ) {
                    var i,
                        current;

                    if ( typeof pipe === "string" ) {
                        // pipe is a string so use
                        delete this.pipes[ pipe ];
                    } else if ( isArray( pipe ) ) {
                        // pipe is an array so loop through each item in the array
                        for ( i = 0; i < pipe.length; i++ ) {
                            current = pipe[ i ];

                            if ( typeof current === "string" ) {
                                delete this.pipes[ current ];
                            } else {
                                delete this.pipes[ current.name ];
                            }
                        }
                    } else if ( pipe ) {
                        // pipe is an object so use that signature
                        delete this.pipes[ pipe.name ];
                    }

                    return this;
                }
            };

        return pipeline.add( pipe );
    };

    /**
     * aerogear.pipeline.adapters
     *
     * The adapters object is provided so that adapters can be added to the aerogear.pipeline namespace dynamically and still be accessible to the add method
     **/
    aerogear.pipeline.adapters = {};
})( aerogear );

(function( aerogear, $, undefined ) {
    // TODO: Share this across entire lib
    function isArray( obj ) {
        return ({}).toString.call( obj ) === "[object Array]";
    }

    /**
     * aerogear.pipeline.adapters.rest
     *
     * The REST adapter is the default type used when creating a new pipe. It uses jQuery.ajax to communicate with the server. By default, the RESTful endpoint used by this pipe is the app's current context, followed by the pipe name. For example, if the app is running on http://mysite.com/myApp, then a pipe named `tasks` would use http://mysite.com/myApp/tasks as its REST endpoint.
     *
     * `aerogear.pipeline.adapters.rest( pipeName [, recordId, ajaxSettings] ) -> Object`
     * - pipeName (String): the name that will be used to reference this pipe
     * - recordId (String): the record identifier specified when the pipe was created
     * - ajaxSettings (Object) - an object used to pass additional parameters to jQuery.ajax
     *
     * When creating a new pipe using the REST adapter, the `settings` parameter to be supplied to pipeline is a hash of key/value pairs that will be supplied to the jQuery.ajax method.
     *
     * Once created, the new pipe will contain:
     * - **recordId** - the record identifier specified when the pipe was created
     * - **type** - the type specified when the pipe was created
     * - **data** - an object used to store a client side copy of the data associated with this pipe
     **/
    aerogear.pipeline.adapters.rest = function( pipeName, recordId, ajaxSettings ) {
        ajaxSettings = $.extend({
            // use the pipeName as the default rest endpoint
            url: pipeName,
            // set the default content type and Accept headers to JSON
            contentType: "application/json",
            dataType: "json"
        }, ajaxSettings );

        return {
            recordId: recordId,
            type: "rest",
            data: null,
            /**
             * aerogear.pipeline.adapters.rest#read( [options] ) -> Object
             * - options (Object): Additional options
             *
             * The options sent to read can include either of the following:
             *  - **data** - Object, a hash of key/value pairs that can be passed to the server as additional information for use when determining what data to return (Optional)
             *  - **ajax** - Object, a hash of key/value pairs that will be added to or override any AJAX settings set during creation of the pipe using this adapter (Optional)
             *
             * Returns a jqXHR which implements the Promise interface. See the [Defered Object](http://api.jquery.com/category/deferred-object/) reference on the jQuery site for more information.
             *
             *     var myPipe = aerogear.pipeline( "tasks" ).pipes[ 0 ];
             *
             *     // Get a set of key/value pairs of all data on the server associated with this pipe
             *     var allData = myPipe.read();
             *
             *     // A data object can be passed to filter the data and in the case of REST,
             *     // this object is converted to query string parameters which the server can use.
             *     // The values would be determined by what the server is expecting
             *     var filteredData = myPipe.read({
             *         data: {
             *             limit: 10,
             *             date: "2012-08-01"
             *             ...
             *         }
             *     });
             *
             * Example returned data in allData:
             *
             *     [
             *         {
             *             id: 12345
             *             title: "Do Something",
             *             date: "2012-08-01",
             *             ...
             *         },
             *         {
             *             id: 67890
             *             title: "Do Something Else",
             *             date: "2012-08-02",
             *             ...
             *         },
             *         ...
             *     ]
             *
             **/
            read: function( options ) {
                var that = this,
                    data;
                if ( options ) {
                    if ( options.ajax && options.ajax.data ) {
                        data = options.ajax.data;
                    } else if ( options.data ) {
                        data = options.data;
                    } else if ( !options.ajax ) {
                        options.ajax = {};
                    }
                    if ( data ) {
                        options.ajax.data = data;
                    }
                } else {
                    options = { ajax: {} };
                }

                var success = function( data ) {
                    that.data = isArray( data ) ? data : [ data ];

                    if ( options.ajax.success ) {
                        options.ajax.success.apply( this, arguments );
                    }
                };

                return $.ajax( $.extend( {}, ajaxSettings, { type: "GET" }, options.ajax, { success: success } ) );
            },

            /**
             * aerogear.pipeline.adapters.rest#save( data[, options] ) -> Object
             * - data (Object): For new data, this will be an object representing the data to be saved to the server. For updating data, a hash of key/value pairs one of which must be the `recordId` you set during creation of the pipe representing the identifier the server will use to update this record and then any other number of pairs representing the data. The data object is then stringified and passed to the server to be processed.
             * - options (Object): An object with a single key/value pair, the key being `ajax`, that will be added to or override any ajax settings set during creation of the pipe using this adapter
             *
             * Save data asynchronously to the server. If this is a new object (doesn't have a record identifier provided by the server), the data is created on the server (POST) and then that record is sent back to the client including the new server-assigned id, otherwise, the data on the server is updated (PUT).
             *
             * Returns a jqXHR which implements the Promise interface. See the [Defered Object](http://api.jquery.com/category/deferred-object/) reference on the jQuery site for more information.
             *
             *     var myPipe = aerogear.pipeline( "tasks" ).pipes[ 0 ];
             *
             *     // Store a new task
             *     myPipe.save({
             *         title: "Created Task",
             *         date: "2012-07-13",
             *         ...
             *     });
             *
             *     // Pass a success and error callback, in this case using the REST pipe and jQuery.ajax so the functions take the same parameters.
             *     myPipe.save({
             *         title: "Another Created Task",
             *         date: "2012-07-13",
             *         ...
             *     },
             *     {
             *         ajax: {
             *             success: function( data, textStatus, jqXHR ) {
             *                 console.log( "Success" );
             *             },
             *             error: function( jqXHR, textStatus, errorThrown ) {
             *                 console.log( "Error" );
             *             }
             *         }
             *     });
             *
             *     // Update an existing piece of data
             *     var toUpdate = myPipe.data[ 0 ];
             *     toUpdate.data.title = "Updated Task";
             *     myPipe.save( toUpdate );
             *
             **/
            save: function( data, options ) {
                var that = this,
                    type,
                    url,
                    itemFound = false;
                data = data || {};
                options = options || {};
                type = data[ this.recordId ] ? "PUT" : "POST";

                if ( !options.ajax.url && data[ this.recordId ] ) {
                    url = ajaxSettings.url + "/" + data[ this.recordId ];
                } else if ( !options.ajax.url ) {
                    url = ajaxSettings.url;
                } else {
                    url = options.ajax.url;
                }

                var success = function( data ) {
                    if ( that.data ) {
                        for( var item in that.data ) {
                            if ( that.data[ item ].id === data.id ) {
                                that.data[ item ] = data;
                                itemFound = true;
                            }
                        }
                        if ( !itemFound ) {
                            that.data.push( data );
                        }
                    } else {
                        that.data = isArray( data ) ? data : [ data ];
                    }

                    if ( options.ajax.success ) {
                        options.ajax.success.apply( this, arguments );
                    }
                };

                if ( typeof data !== "string" ) {
                    data = JSON.stringify( data );
                }

                return $.ajax( $.extend( {}, ajaxSettings,
                    {
                        data: data,
                        type: type,
                        url: url
                    },
                    options.ajax || {},
                    { success: success }
                ));
            },

            /**
             * aerogear.pipeline.adapters.rest#remove( toRemove [, options] ) -> Object
             * - toRemove (Mixed): A variety of objects can be passed to remove to specify the item to remove as illustrated below
             * - options (Object): An object with a single key/value pair, the key being `ajax`, that will be added to or override any ajax settings set during creation of the pipe using this adapter
             *
             * Remove data asynchronously from the server. Passing nothing will inform the server to remove all data at this pipe's rest endpoint.
             *
             * Returns a jqXHR which implements the Promise interface. See the [Defered Object](http://api.jquery.com/category/deferred-object/) reference on the jQuery site for more information.
             *
             *     var myPipe = aerogear.pipeline( "tasks" ).pipes[ 0 ];
             *
             *     // Store a new task
             *     myPipe.save({
             *         title: "Created Task"
             *     });
             *
             *     // Store another new task
             *     myPipe.save({
             *         title: "Another Created Task"
             *     });
             *
             *     // Store one more new task
             *     myPipe.save({
             *         title: "And Another Created Task"
             *     });
             *
             *     // Remove a particular item from the server by its id
             *     var toRemove = myPipe.data[ 0 ];
             *     myPipe.remove( toRemove.id );
             *
             *     // Remove an item from the server using the data object
             *     toRemove = myPipe.data[ 0 ];
             *     myPipe.remove( toRemove );
             *
             *     // Delete all remaining data from the server associated with this pipe
             *     myPipe.delete();
             *
             **/
            remove: function( toRemove, options ) {
                var that = this,
                    delId = 0,
                    delPath = "",
                    url;

                options = options || {};

                if ( typeof toRemove === "string" || typeof toRemove === "number" ) {
                    delId = parseInt( toRemove, 10 );
                } else if ( toRemove ) {
                    if ( typeof toRemove.record === "string" || typeof toRemove.record === "number" ) {
                        delId = parseInt( toRemove.record, 10 );
                    } else if ( toRemove.record ) {
                        delId = parseInt( toRemove.record[ this.recordId ], 10 );
                    }
                } else {
                    throw "aerogear.pipeline.rest: missing argument";
                }

                delPath = delId ? "/" + delId : "";
                if ( options.ajax ) {
                    if ( options.ajax.url ) {
                        url = options.ajax.url;
                    } else {
                        url = ajaxSettings.url + delPath;
                    }
                } else if ( toRemove.ajax ) {
                    options.ajax = toRemove.ajax;
                    if ( toRemove.ajax.url ) {
                        url = toRemove.ajax.url;
                    } else {
                        url = ajaxSettings.url + delPath;
                    }
                } else {
                    url = ajaxSettings.url + delPath;
                }

                var success = function( data ) {
                    var itemIndex;

                    for( var item in that.data ) {
                        if ( that.data[ item ].id === delId ) {
                            that.data.splice( item, 1 );
                        }
                    }

                    if ( options.ajax.success ) {
                        options.ajax.success.apply( this, arguments );
                    }
                };

                return $.ajax( $.extend( {}, ajaxSettings,
                    {
                        type: "DELETE",
                        url: url
                    },
                    options.ajax || {},
                    { success: success }
                ));
            }
        };
    };
})( aerogear, jQuery );
