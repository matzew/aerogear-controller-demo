/**
 * JBoss, Home of Professional Open Source
 * Copyright Red Hat, Inc., and individual contributors
 * by the @authors tag. See the copyright.txt in the distribution for a
 * full listing of individual contributors.
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
package org.jboss.aerogear.controller.demo;

import org.jboss.aerogear.controller.demo.config.CustomMediaTypeResponder;
import org.jboss.aerogear.controller.demo.model.Car;
import org.jboss.aerogear.controller.router.AbstractRoutingModule;
import org.jboss.aerogear.controller.router.MediaType;
import org.jboss.aerogear.controller.router.RequestMethod;
import org.jboss.aerogear.controller.router.parameter.MissingRequestParameterException;
import org.jboss.aerogear.controller.router.rest.pagination.PaginationInfo;
import org.jboss.aerogear.controller.router.rest.pagination.PaginationRequestException;
import org.jboss.aerogear.security.shiro.model.User;

import static org.jboss.aerogear.controller.demo.config.CustomMediaTypeResponder.CUSTOM_MEDIA_TYPE;

/**
 * Routes are the core of aerogear-controller–demo.
 * It's where we bind the the application business controller {@link Home}
 * to the URL it responds.<br>
 * All the configuration is done with a type safe DSL.
 *
 * @see Home
 */

public class Routes extends AbstractRoutingModule {

    /**
     * Entry point for configuring the routes mapping http requests to the pojo controllers
     */
    @Override
    public void configuration() throws Exception {

        route()
                .on(CarNotFoundException.class)
                .produces(JSON)
                .to(Error.class).respondWithErrorStatus(param(CarNotFoundException.class));
        route()
                .on(PaginationRequestException.class)
                .produces(JSON)
                .to(Error.class).handlePagingRequestException(param(PaginationRequestException.class));
        route()
                .on(MissingRequestParameterException.class)
                .produces(JSON)
                .to(Error.class).handleMissingRequestParameter(param(MissingRequestParameterException.class));

        route()
                .on(Exception.class)
                .produces(JSP, JSON)
                .to(Error.class).index(param(Exception.class));
        route()
                .from("/")
                .on(RequestMethod.GET)
                .to(Home.class).index();
        route()
                .from("/delorean").roles("admin")
                .on(RequestMethod.GET)
                .produces(JSON, JSP)
                .consumes(JSON, JSP)
                .to(Home.class).anotherPage();
        route()
                .from("/cars")
                .on(RequestMethod.POST)
                .consumes(JSON, HTML)
                .produces(JSON, JSP, CUSTOM_MEDIA_TYPE)
                .to(Cars.class).save(param(Car.class));
        route()
                .from("/cars")
                .on(RequestMethod.GET)
                .produces(JSON, CUSTOM_MEDIA_TYPE)
                .to(Cars.class).findCarsBy(param(PaginationInfo.class), param("color"));
        route()
                .from("/cars-custom")
                .on(RequestMethod.GET)
                .produces(JSON, CUSTOM_MEDIA_TYPE)
                .to(Cars.class).findCarsByCustomHeaders(param(PaginationInfo.class), param("color"));
        route()
                .from("/cars/{id}")
                .on(RequestMethod.GET)
                .produces(MediaType.JSON)
                .to(Cars.class).findById(param("id"));
        route()
                .from("/mycars")
                .on(RequestMethod.GET)
                .produces(JSON, JSP)
                .to(Cars.class).mycars();
        route()
                .from("/autobots")
                .on(RequestMethod.GET)
                .produces(JSON)
                .to(Cars.class).autobots();
        route()
                .from("/login")
                .on(RequestMethod.GET)
                .produces(JSP, CustomMediaTypeResponder.CUSTOM_MEDIA_TYPE)
                .to(Login.class).index();
        route()
                .from("/login")
                .on(RequestMethod.POST)
                .produces(JSP, CustomMediaTypeResponder.CUSTOM_MEDIA_TYPE)
                .consumes(JSP, CustomMediaTypeResponder.CUSTOM_MEDIA_TYPE)
                .to(Login.class).login(param(User.class));

        route()
                .from("/logout")
                .on(RequestMethod.GET, RequestMethod.POST)
                .produces(JSON, JSP)
                .consumes(JSON, JSP)
                .to(Login.class).logout();

        route()
                .from("/register")
                .on(RequestMethod.GET)
                .to(Register.class).index();

        route()
                .from("/register")
                .on(RequestMethod.POST)
                .produces(JSON, JSP)
                .consumes(JSON, JSP)
                .to(Register.class).register(param(User.class));

        route()
                .from("/admin").roles("admin")
                .on(RequestMethod.GET)
                .to(Admin.class).index();
//        route()
//                .from("/admin").roles("admin")
//                .on(RequestMethod.POST)
//                .produces(JSON, JSP)
//                .consumes(JSON, JSP)
//                .to(Admin.class).register(param("username"), param("password"));
        /*route()
                .from("/show/{id}").roles("admin")
                .on(RequestMethod.GET)
                .to(Admin.class).show(param("id"));
        route()
                .from("/show/remove").roles("admin")
                .on(RequestMethod.POST)
                .to(Admin.class).remove(param(User.class));

         route()
                .from("/otp")
                .on(RequestMethod.POST)
                .produces(JSON, JSP)
                .consumes(JSON, JSP)
                .to(Otp.class).otp(param(User.class));
        route()
                .from("/auth/otp/secret")
                .on(RequestMethod.GET)
                .produces(JSON)
                .to(Otp.class).secret();


        route()
                .from("/throwException")
                .on(RequestMethod.GET)
                .produces(JSP, JSON)
                .to(Error.class).throwException();

        route()
                .from("/html")
                .on(RequestMethod.GET)
                .produces(HTML)
                .to(Html.class).index();

        route()
                .on(AeroGearSecurityException.class)
                .produces(JSP, JSON)
                .to(Error.class).security(param(RuntimeException.class));

        /*
         * This error route is only for demo purposes and we do not recommend a production system
         * to provide this much information, as it could be used by an attacker.
         *//*
        route()
                .on(UnexpectedCredentialException.class)
                .produces(JSP)
                .to(Error.class).alreadyLoggedIn();*/
    }
}
