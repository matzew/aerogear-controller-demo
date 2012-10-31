package org.jboss.aerogear.controller.demo;

import org.jboss.aerogear.controller.demo.model.User;
import org.jboss.aerogear.security.auth.AuthenticationManager;

import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class Login {

    @Inject
    private AuthenticationManager authenticationManager;

    public void index() {
        System.out.println("Login page!");
    }

    public void otp() {
        System.out.println("OTP Login page!");
    }

    public User login(User user) {
        System.out.println("Username: " + user.getId());
        System.out.println("Password: " + user.getPassword());
        System.out.println("OTP: " + user.getOtp());

        authenticationManager.login(user);

        return user;
    }

    public void logout() {
        System.out.println("User logout!");
    }
}
