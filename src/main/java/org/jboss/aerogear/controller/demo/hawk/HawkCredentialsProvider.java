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
