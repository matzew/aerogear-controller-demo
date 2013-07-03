package org.jboss.aerogear.controller.demo.config;

import com.wealdtech.hawk.HawkServer;
import com.wealdtech.hawk.HawkServerConfiguration;

import javax.enterprise.inject.Produces;

public class HawkConfigProducer {

    @Produces
    public HawkServer hawkConfig() {
        HawkServerConfiguration configuration = new HawkServerConfiguration.Builder().build();
        return new HawkServer.Builder().configuration(configuration).build();
    }
}
