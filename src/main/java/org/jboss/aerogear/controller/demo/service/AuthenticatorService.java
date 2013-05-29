package org.jboss.aerogear.controller.demo.service;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.Sha512Hash;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.subject.Subject;
import org.jboss.aerogear.controller.demo.config.SecurityRealm;
import org.jboss.aerogear.controller.demo.model.User;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.logging.Logger;

public class AuthenticatorService {

    @Inject
    private EntityManager entityManager;

    @Inject
    private Subject subject;

    private static final Logger LOGGER = Logger.getLogger(AuthenticatorService.class.getSimpleName());

    public User register(String username, String password) {
        User user = new User(username, new Sha512Hash(password).toHex());
        entityManager.persist(user);
        return user;
    }

    public User login(final User user) {
        UsernamePasswordToken token = new UsernamePasswordToken(user.getUsername(), user.getPassword());
        try {
            subject.login(token);
            LOGGER.info("Session: " + subject.getSession().getId());
            LOGGER.info("IS AUTHENTICATED? " + subject.isAuthenticated());
        } catch (AuthenticationException e) {
            throw new RuntimeException("Authentication failed");
        }
        return user;
    }

    public void logout() {
        subject.logout();
    }

}
