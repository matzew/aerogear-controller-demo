/*
 * JBoss, Home of Professional Open Source
 * Copyright 2012, Red Hat, Inc., and individual contributors
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
package org.jboss.aerogear.controller.demo.rest;

import org.jboss.aerogear.controller.demo.model.User;
import org.jboss.aerogear.security.dsl.AuthenticationManager;
import org.jboss.aerogear.security.dsl.IDMConfiguration;
import org.jboss.logging.Logger;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Stateless
@Path("/auth")
public class AuthenticationEndpoint {

    private static final Logger LOGGER = Logger.getLogger(AuthenticationEndpoint.class);
    public static final String DEFAULT_ROLE = "admin";

    @Inject
    private AuthenticationManager authenticationManager;

    @Inject
    private IDMConfiguration configuration;

    @POST
    @Path("/register")
    @Produces(MediaType.APPLICATION_JSON)
    public Response register(final User user) {

        //TODO it should be done by admin screen
        configuration.grant(DEFAULT_ROLE).to(user);

        authenticationManager.login(user);

        return Response.ok(user).build();
    }

    @POST
    @Path("/login")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response login(User user) {

        LOGGER.debug("Logged in!");

        authenticationManager.login(user);

        return Response.ok(user).build();
    }

    @POST
    @Path("/logout")
    @Produces(MediaType.APPLICATION_JSON)
    public void logout() {
        authenticationManager.logout();
    }

}