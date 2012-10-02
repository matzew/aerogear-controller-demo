package org.jboss.aerogear.controller.demo;

import org.jboss.aerogear.controller.demo.model.User;
import org.jboss.aerogear.security.dsl.AuthenticationManager;
import org.jboss.aerogear.security.dsl.IDMHelper;
import org.jboss.aerogear.security.model.AeroGearUser;
import org.jboss.picketlink.idm.model.SimpleUser;

import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class Register {

    @Inject
    private IDMHelper idm;

    @Inject
    private AuthenticationManager authenticationManager;

    public void index() {
        System.out.println("Login page!");
    }

    public AeroGearUser register(User user) {

        idm.grant("admin").to(user);
        boolean logged = authenticationManager.login(user.getId(), user.getPassword());
        return user;
    }
}
