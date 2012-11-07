/***
 * JBoss, Home of Professional Open Source
 * Copyright 2012, Red Hat, Inc., and individual contributors
 * by the @authors tag. See the copyright.txt in the distribution for a
 * full listing of individual contributors.

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

import org.jboss.aerogear.controller.RequestMethod;
import org.jboss.aerogear.controller.demo.model.Car;
import org.jboss.aerogear.controller.demo.model.User;
import org.jboss.aerogear.controller.router.AbstractRoutingModule;
import org.jboss.aerogear.security.exception.AeroGearSecurityException;

/**
 * Routes are the core of aerogear-controller–demo.
 * It's where we bind the the application bussines controller {@link Home}
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
    public void configuration() {

        route()
                .on(AeroGearSecurityException.class)
                .to(Error.class).security();
        route()
                .on(Exception.class)
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
                .to(Home.class).save(param(Car.class));
        route()
                .from("/login")
                .on(RequestMethod.GET)
                .to(Login.class).index();
        route()
                .from("/login")
                .on(RequestMethod.POST)
                .to(Login.class).login(param(User.class));
        route()
                .from("/otp")
                .on(RequestMethod.GET)
                .to(Otp.class).index();
        route()
                .from("/otp")
                .on(RequestMethod.POST)
                .to(Otp.class).otp(param(User.class));
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
                .to(Register.class).register(param(User.class));

        route()
                .from("/throwException")
                .on(RequestMethod.GET)
                .to(Error.class).throwException();
    }
}
