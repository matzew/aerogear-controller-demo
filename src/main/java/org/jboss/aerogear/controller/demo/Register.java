package org.jboss.aerogear.controller.demo;

import org.jboss.aerogear.controller.demo.model.User;
import org.jboss.aerogear.security.auth.AuthenticationManager;
import org.jboss.aerogear.security.authz.IdentityManagement;

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

    public User register(User user) {

        configuration.grant(DEFAULT_ROLE).to(user);
        authenticationManager.login(user);
        return user;
    }
}
