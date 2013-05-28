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

import javax.ejb.Stateless;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;
import java.util.logging.Logger;

@Stateless
public class Otp {

    @Inject
    private Instance<String> secret;

    @Inject
    private Instance<String> loggedInUserName;

    private static final Logger LOGGER = Logger.getLogger(Otp.class.getSimpleName());
    
   /* public AeroGearUser secret() {
        AeroGearUser user = new AeroGearUser();
        user.setUri(new Totp(secret.get()).uri(loggedInUserName.get()));
        return user;
    }

    public AeroGearUser otp(AeroGearUser user) {

        Totp totp = new Totp(secret.get());
        boolean result = totp.verify(user.getOtp());

        if (!result)
            throw new RuntimeException("Invalid OTP");

        return user;
    }*/
}
