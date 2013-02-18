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

import static org.jboss.aerogear.controller.demo.config.CustomMediaTypeResponder.CUSTOM_MEDIA_TYPE;
import org.jboss.aerogear.controller.demo.model.Car;
import org.jboss.aerogear.controller.router.AbstractRoutingModule;
import org.jboss.aerogear.controller.router.MediaType;
import org.jboss.aerogear.controller.router.RequestMethod;
import org.jboss.aerogear.controller.router.parameter.MissingRequestParameterException;
import org.jboss.aerogear.controller.router.rest.pagination.PaginationInfo;
import org.jboss.aerogear.controller.router.rest.pagination.PagingRequestException;
import org.jboss.aerogear.security.exception.AeroGearSecurityException;
import org.jboss.aerogear.security.model.AeroGearUser;

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
                .on(PagingRequestException.class)
                .produces(JSON)
                .to(Error.class).handlePagingRequestException(param(PagingRequestException.class));
        route()
                .on(MissingRequestParameterException.class)
                .produces(JSON)
                .to(Error.class).handleMissingRequestParameter(param(MissingRequestParameterException.class));
        route()
                .on(AeroGearSecurityException.class)
                .produces(JSP, JSON)
                .to(Error.class).security();
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
                .from("/login")
                .on(RequestMethod.GET)
                .to(Login.class).index();
        route()
                .from("/login")
                .on(RequestMethod.POST)
                .to(Login.class).login(param(AeroGearUser.class));
        route()
                .from("/otp")
                .on(RequestMethod.POST)
                .to(Otp.class).otp(param(AeroGearUser.class));
        route()
                .from("/logout")
                .on(RequestMethod.GET)
                .to(Login.class).logout();
        route()
                .from("/register")
                .on(RequestMethod.GET)
                .to(Register.class).index();
        route()
                .from("/register")
                .on(RequestMethod.POST)
                .to(Register.class).register(param(AeroGearUser.class));
        route()
                .from("/throwException")
                .on(RequestMethod.GET)
                .produces(JSP, JSON)
                .to(Error.class).throwException();
        route()
                .from("/admin").roles("admin")
                .on(RequestMethod.GET)
                .to(Admin.class).index();
        route()
                .from("/admin").roles("admin")
                .on(RequestMethod.POST)
                .to(Admin.class).register(param(AeroGearUser.class));
        route()
                .from("/show/{id}").roles("admin")
                .on(RequestMethod.GET)
                .to(Admin.class).show(param("id"));
        route()
                .from("/show/remove").roles("admin")
                .on(RequestMethod.POST)
                .to(Admin.class).remove(param(AeroGearUser.class));
        route()
                .from("/html")
                .on(RequestMethod.GET)
                .produces(HTML)
                .to(Html.class).index();
    }
}
