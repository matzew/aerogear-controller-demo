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

import org.jboss.aerogear.security.otp.Totp;
import org.jboss.aerogear.security.otp.api.Base32;
import org.picketlink.Identity;
import org.picketlink.idm.IdentityManager;
import org.picketlink.idm.model.Agent;
import org.picketlink.idm.model.Attribute;
import org.picketlink.idm.model.SimpleUser;
import org.picketlink.idm.model.User;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.logging.Logger;

@Stateless
public class Otp {

    @Inject
    private Identity identity;

    @Inject
    private IdentityManager identityManager;

    private static final String IDM_SECRET_ATTRIBUTE = "secret";

    public String secret() {
        String secret = getSecret();
        return new Totp(secret).uri(identity.getAgent().getLoginName());
    }

    public User otp(SimpleUser user, String otp) {

        String secret = getSecret();
        Totp totp = new Totp(secret);
        boolean result = totp.verify(otp);

        if (!result)
            throw new RuntimeException("Invalid OTP");

        return user;
    }

    public String getSecret() {

        Attribute<String> secret = null;

        if (identity.isLoggedIn()) {

            Agent user = identity.getAgent();

            secret = user.getAttribute(IDM_SECRET_ATTRIBUTE);

            if (secret == null) {
                secret = new Attribute<String>(IDM_SECRET_ATTRIBUTE, Base32.random());
                user.setAttribute(secret);
                this.identityManager.update(user);
            }
        }
        return secret.getValue();
    }
}
