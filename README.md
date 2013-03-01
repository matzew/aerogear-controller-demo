# AeroGear Controller Demo - very lean mvc controller
[AeroGear](http://aerogear.org) Controller is a very lean mvc controller written in Java. It focuses on the routing of HTTP requests to plain Java object endpoint
and the handling of the results, by either forwarding the data to a view, or returning the data in the format requested by the caller.
This project show cases some of the functionality of AeroGear Controller.  

An instance of this demo is deployed on [OpenShift](https://controller-aerogear.rhcloud.com/aerogear-controller-demo/) and can 
be used to try it out. Please refer to the [installation](#install) section for deploying locally.

## Demo Contents
This demo project has a number of [routes](https://github.com/aerogear/aerogear-controller-demo/blob/master/src/main/java/org/jboss/aerogear/controller/demo/Routes.java#L45), 
some are used in a MVC fashion while others are expected to be called as RESTful resources.

### Model View Controller Routes
These routes are used for the web based interface which you can find on [OpenShift](https://controller-aerogear.rhcloud.com/aerogear-controller-demo/). 
Please refer to the [user guide](http://aergear.org/docs/guides/aerogear-controller) for detailed information about configuring routes.

### RESTful Controller Routes
There are few routes that are intended to respond with JSON data. These routes deal with the ```/cars``` resource and demonstrate 
[pagination](http://aergear.org/docs/guides/aerogear-controller/pagination.html).  
The basic idea is that a client wants to limit the number of _cars_ it receives per request to a certain number.

#### Requesting the first set of cars:

     curl -i --header "Accept: application/json" "https://controller-aerogear.rhcloud.com/aerogear-controller-demo/cars?offset=0&color=red&limit=5"

Running the above command will produce output similar to the below:  

    HTTP/1.1 200 OK
    Link: <http://controller-aerogear.rhcloud.com/aerogear-controller-demo/cars?offset=5&color=red&limit=5>; rel="next"
    Content-Type: application/json;charset=UTF-8
    Content-Length: 194
    [
      {"color":"red","brand":"Audi","id":6},
      {"color":"red","brand":"BMW","id":13},
      {"color":"red","brand":"Fiat","id":20},
      {"color":"red","brand":"Golf","id":27},
      {"color":"red","brand":"Lada","id":34}
    ]
By default, AeroGear Controller uses [Web Linking](http://tools.ietf.org/html/rfc5988) specification and the ```Link``` header
above is an example of this. A client can use these links to navigate, to the next/previous page.

#### Requesting the next set of cars:
To retrieve the next set of cars, use the URL from ```next``` (see the ```Link``` header in the previous example):

     curl -i --header "Accept: application/json" "http://controller-aerogear.rhcloud.com/aerogear-controller-demo/cars?offset=5&color=red&limit=5"

## <a id="install"></a>Installation
Building the project is done using maven:
    
    mvn install
    
### Deploy
An AeroGear Controller application can be deployed to any application server with a CDI container.  
However, this demo uses a datasource that by default exists in JBoss AS 7.x and it is the preferred deployment environment.

#### Manual deployment

    $ cp target/aerogear-controller-demo.war $AS7_HOME/standalone/deployments

#### CLI deployment

    $ cd $AS7_HOME
    $ ./jboss-cli.sh --connect
    $ [standalone@localhost:9999 /] deploy /path/to/aerogear-controller-demo/target/aerogear-controller-demo.war

#### Testing Basic authentication

curl -v --basic -u john:123 "http://localhost:8080/aerogear-controller-demo/mycars"

#### Testing Digest authentication

curl -v --digest -u john:123 "http://localhost:8080/aerogear-controller-demo/mycars"
     
## Documentation
* [User Guide](http://aergear.org/docs/guides/aerogear-controller)
* [API](http://aerogear.org/docs/specs/aerogear-controller)
* [REST API](http://aerogear.org/docs/specs/aerogear-rest-api)
* [JBosss CLI](https://community.jboss.org/wiki/CommandLineInterface)

## Community
* [User Forum](https://community.jboss.org/en/aerogear?view=discussions)
* [Developer Mailing List](http://aerogear-dev.1069024.n5.nabble.com)

## Issue Tracker
* [JIRA](https://issues.jboss.org/browse/AEROGEAR)