/**
 * JBoss, Home of Professional Open Source
 * Copyright Red Hat, Inc., and individual contributors
 * by the @authors tag. See the copyright.txt in the distribution for a
 * full listing of individual contributors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.jboss.aerogear.controller.demo;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.mgt.SecurityManager;
import org.jboss.aerogear.controller.demo.config.SecurityRealm;
import org.jboss.aerogear.controller.demo.model.User;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.logging.Logger;

@Stateless
public class Login {

    @Inject
    private EntityManager em;

    @Inject
    private SecurityRealm realm;

    private Subject subject;

    private static final Logger LOGGER = Logger.getLogger(Login.class.getSimpleName());

    @PostConstruct
    public void init(){
        SecurityManager securityManager = new DefaultSecurityManager(realm);
        SecurityUtils.setSecurityManager(securityManager);
        subject = SecurityUtils.getSubject();
    }

    public void index() {
        LOGGER.info("Login page!");
    }

    public User login(final User user) {

        System.out.println("Username: " + user.getUsername());
        System.out.println("Password: " + user.getPassword());

        UsernamePasswordToken token = new UsernamePasswordToken(user.getUsername(), "admin");
        subject.login(token);
        System.out.println("Hi there, do we have session? " + subject.getSession().getId());
        return user;
    }

    public void logout() {
        LOGGER.info("User logout!");
        subject.logout();
    }


}
