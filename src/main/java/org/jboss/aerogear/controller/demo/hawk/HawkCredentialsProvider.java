/**
 * JBoss, Home of Professional Open Source
 * Copyright Red Hat, Inc., and individual contributors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.jboss.aerogear.controller.demo.hawk;

import com.wealdtech.hawk.HawkCredentials;
import org.jboss.aerogear.security.auth.HawkCredentialProvider;

public class HawkCredentialsProvider implements HawkCredentialProvider {


    @Override
    public HawkCredentials findByKey(String key) {
        HawkCredentials credentials = new HawkCredentials.Builder()
                .keyId("dh37fgj492je")
                .key("werxhqb98rpaxn39848xrunpaw3489ruxnpa98w4rxn")
                .algorithm(HawkCredentials.Algorithm.SHA256)
                .build();
        return credentials;
    }
}
