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

import org.jboss.aerogear.controller.demo.model.User;
import org.jboss.aerogear.controller.demo.service.AuthenticatorService;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

@Stateless
public class Admin {

    @Inject
    private AuthenticatorService authenticatorService;

    public List index() {
//        return configuration.findAllByRole("simple");
        return new ArrayList();
    }

    public List register(String username, String password) {
        User user = authenticatorService.register(username, password);
        authenticatorService.login(user);
        return new ArrayList();
    }

    /*public List remove(AeroGearUser aeroGearUser) {
        configuration.remove(aeroGearUser);
        return configuration.findAllByRole("simple");
    }

    public AeroGearUser show(String id){
       return configuration.get(id);
    }*/
}
