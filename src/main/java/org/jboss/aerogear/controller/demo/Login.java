package org.jboss.aerogear.controller.demo;

import org.jboss.aerogear.controller.demo.model.User;
import org.jboss.aerogear.security.dsl.AuthenticationManager;
import org.jboss.aerogear.security.dsl.IDMHelper;

import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class Login {

    @Inject
    private AuthenticationManager authenticationManager;

    @Inject
    private IDMHelper idm;

    public void index() {
        System.out.println("Login page!");
    }

    public User login(User user) {

        System.out.println(user.getName());
        System.out.println(user.getPassword());

        boolean logged = authenticationManager.login(user.getName(), user.getPassword());
        System.out.println("Logged? " + logged);
        return user;
    }

    public User register(User user) {
        System.out.println(user.getName());
        System.out.println(user.getPassword());

//        idm.grant(user.getRole()).to(user);
//        boolean logged = authenticationManager.login(user.getUsername(), user.getPassword());
//        System.out.println("Registered? " + logged);
        return user;
    }

    public void logout() {
        System.out.println("User logout!");
    }
}
