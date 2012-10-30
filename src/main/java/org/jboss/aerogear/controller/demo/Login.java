package org.jboss.aerogear.controller.demo;

import org.jboss.aerogear.controller.demo.model.User;
import org.jboss.aerogear.security.auth.AuthenticationManager;
import org.jboss.aerogear.security.model.AeroGearUser;

import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class Login {

    @Inject
    private AuthenticationManager authenticationManager;

    public void index() {
        System.out.println("Login page!");
    }

    public User login(User user) {

        System.out.println(user.getId());
        System.out.println(user.getPassword());
        authenticationManager.login(user);

        return user;
    }

    public void logout() {
        System.out.println("User logout!");
    }
}
