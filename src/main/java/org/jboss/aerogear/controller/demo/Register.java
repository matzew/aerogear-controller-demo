package org.jboss.aerogear.controller.demo;

import org.jboss.aerogear.controller.demo.model.User;
import org.jboss.aerogear.security.dsl.AuthenticationManager;
import org.jboss.aerogear.security.dsl.IdentityManagement;
import org.jboss.aerogear.security.model.AeroGearUser;

import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class Register {

    public static final String DEFAULT_ROLE = "admin";
    @Inject
    private IdentityManagement configuration;

    @Inject
    private AuthenticationManager authenticationManager;

    public void index() {
        System.out.println("Login page!");
    }

    public AeroGearUser register(User user) {

        configuration.grant(DEFAULT_ROLE).to(user);
        authenticationManager.login(user);
        return user;
    }
}
