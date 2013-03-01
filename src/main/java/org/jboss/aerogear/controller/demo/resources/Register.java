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

package org.jboss.aerogear.controller.demo.resources;

import org.jboss.aerogear.controller.demo.exception.HttpSecurityException;
import org.jboss.aerogear.controller.demo.exception.HttpStatus;
import org.picketlink.Identity;
import org.picketlink.credential.DefaultLoginCredentials;
import org.picketlink.idm.IdentityManager;
import org.picketlink.idm.credential.Password;
import org.picketlink.idm.model.Role;
import org.picketlink.idm.model.SimpleUser;
import org.picketlink.idm.model.User;

import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class Register {

    public static final String DEFAULT_ROLE = "simple";

    @Inject
    private IdentityManager identityManager;

    @Inject
    private DefaultLoginCredentials credentials;

    @Inject
    private Identity identity;

    public void index() {
        System.out.println("Login page!");
    }

    public User register(SimpleUser simpleUser, String password) {
        identityManager.add(simpleUser);
        //Default password until figure out how to add this at our views
        identityManager.updateCredential(simpleUser, new Password(password));

        Role role = identityManager.getRole(DEFAULT_ROLE);
        identityManager.grantRole(simpleUser, role);

        credentials.setUserId(simpleUser.getLoginName());
        credentials.setCredential(new Password(password));

        if (identity.login() != Identity.AuthenticationResult.SUCCESS) {
            throw new HttpSecurityException(HttpStatus.AUTHENTICATION_FAILED);
        }
        return simpleUser;
    }
}
