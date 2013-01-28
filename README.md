# aerogear-controller-demo - very lean mvc controller

## how to create a new project

### basic use case
1. add the maven dependency

        <dependency>
            <groupId>org.jboss.aerogear</groupId>
            <artifactId>aerogear-controller</artifactId>
            <version>1.0.0.M8</version>
            <scope>compile</scope>
        </dependency>

1. create a pojo controller

        public class Home {
            public void index() {
            }
        }

1. create a Java class containing the routes (must extend `AbstractRoutingModule`)

        public class Routes extends AbstractRoutingModule {

        @Override
        public void configuration() {
            route()
                   .from("/")
                   .on(RequestMethod.GET)
                   .to(Home.class).index();
            }
        }

1. create a jsp page at `/WEB-INF/pages/<Controller Class Name>/<method>.jsp`

        <!-- /WEB-INF/pages/Home/index.jsp -->
        <html>
            <body>
                <p>hello from index!</p>
            </body>
        </html>
        
### populating parameters

You can use immutable beans straight away as controller parameters:

        public class Store {
            public Car save(Car car) {
                return car;
            }
        }

This can be populated by putting a route to it (preferable via post, of course)

        route()
               .from("/cars")
               .on(RequestMethod.POST)
               .to(Store.class).save(param(Car.class));


And you can use a simple html form for it, by just following the convention:

            <input type="text" name="car.color"/>
            <input type="text" name="car.brand"/>

The car object will be automatically populated with the provided values - note that it supports deep linking, so this would work fine too:

            <input type="text" name="car.brand.owner"/>

All the intermediate objects are created automatically.

----

### pagination ###
There are two routes in this demo that support pagination of data. Both respond with ```cars``` and with the difference that one
uses [Web Linking](http://tools.ietf.org/html/rfc5988), and the other used custom HTTP headers to provide links to the next and previous pages as appropriate.  

#### web linking
This is the default in AeroGear Controller and you configure your endpoint similar to this example:

    @Paginated 
    public List<Car> findCarsBy(final PaginationInfo paginationInfo, final String color) {
        return getCars(paginationInfo.getOffset(), color, paginationInfo.getLimit());
    }
    
#### custom headers
To use custom headers you simply need to disable Web Linking:

    @Paginated (webLinking = false)
    public List<Car> findCarsByCustomHeaders(final PaginationInfo paginationInfo, final String color) {
        return getCars(paginationInfo.getOffset(), color, paginationInfo.getLimit());
    }
    
The Paginated annotation can be further customized, for example the name of the
query parameters used, their default values if they are missing from the request, and a custom header prefix. 
Please refer to [Paginated](https://github.com/aerogear/aerogear-controller/tree/master/src/main/java/org/jboss/aerogear/controller/router/rest/pagination/Paginated.java)
JavaDoc for more information.

### resources
1. [aerogear-controller](https://github.com/aerogear/aerogear-controller)
1. [aerogear.org](http://aerogear.org/)
