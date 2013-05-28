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

package org.jboss.aerogear.controller.demo.config;

import org.apache.shiro.crypto.hash.Sha256Hash;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

@Singleton
@Startup
public class ShiroDefaultUsers {


    @Inject
    private EntityManager em;

    /**
     * <p>Loads some users during the first construction.</p>
     */
    //TODO this entire initialization code will be removed
    @PostConstruct
    public void create() {

        em.createNativeQuery("insert into roles values (1, 'user', 'The default role given to all users.')");
        em.createNativeQuery("insert into roles values (2, 'admin', 'The administrator role only given to site admins')");
        em.createNativeQuery("insert into roles_permissions values (2, 'user:*')");
        em.createNativeQuery("insert into users(id,username,email,password) values (1, 'admin', 'sample@shiro.apache.org', '" + new Sha256Hash("admin").toHex() + "')");
        em.createNativeQuery("insert into users_roles values (1, 2)");

        System.out.println("====== " + em.createNativeQuery("select * from roles").getResultList());

        //because we're using an in-memory hsqldb for the sample app, a new one will be created each time the
        //app starts, so insert the sample admin user at startup:
        /*JdbcTemplate jdbcTemplate = new JdbcTemplate(this.dataSource);

        jdbcTemplate.execute("insert into roles values (1, 'user', 'The default role given to all users.')");
        jdbcTemplate.execute("insert into roles values (2, 'admin', 'The administrator role only given to site admins')");
        jdbcTemplate.execute("insert into roles_permissions values (2, 'user:*')");
        jdbcTemplate.execute("insert into users(id,username,email,password) values (1, 'admin', 'sample@shiro.apache.org', '" + new Sha256Hash("admin").toHex() + "')");
        jdbcTemplate.execute("insert into users_roles values (1, 2)");*/


    }
}
