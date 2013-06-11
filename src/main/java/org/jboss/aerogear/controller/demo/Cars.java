/***
 * JBoss, Home of Professional Open Source
 * Copyright 2012, Red Hat, Inc., and individual contributors
 * by the @authors tag. See the copyright.txt in the distribution for a
 * full listing of individual contributors.

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

import org.jboss.aerogear.controller.demo.model.Car;
import org.jboss.aerogear.controller.router.rest.pagination.Paginated;
import org.jboss.aerogear.controller.router.rest.pagination.PaginationInfo;

import javax.annotation.PreDestroy;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceUnit;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.List;

public class Cars {

    @PersistenceUnit (unitName = "cars")
    private EntityManagerFactory emf;
    
    @PreDestroy
    public void closeEntityManagerFactory() {
        emf.close();
    }

    /**
    * Respond to a POST request "/cars". The response page follow
    * a convention, having a <em>folder/jsp</em> at the directory <em>WEB-INF/pages</em>.
    * The folder/jsp are named after the business controller class/method been called, respectively.
    * Ex: <em>WEB-INF/pages/Home/save.jsp</em«
    * The returned <em>Car<em> object is exposed to the <em>save.jsp</em>
    * and might be accessed using Expression Language. The naming convention
    * used to expose the object is the class name in camel case. Ex: <pre>${car.brand}</pre>
    *
    * @return Car
    * @see Routes
    */
    public Car save(final Car car) {
        final EntityManager em = emf.createEntityManager();
        final EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.persist(car);
            tx.commit();
        } catch (final Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }
        return car;
    }
    
    public Car findById(final String id) throws CarNotFoundException {
        final EntityManager em = emf.createEntityManager();
        try {
            return em.createQuery("SELECT c FROM Car c WHERE c.id = :id", Car.class)
                    .setParameter("id", Long.valueOf(id))
                    .getSingleResult();
        } catch (final NoResultException e) {
            throw new CarNotFoundException("Could not find a Car with id=" + id, HttpServletResponse.SC_NOT_FOUND);
        } finally {
            em.close();
        }
    }
    
    @Paginated 
    public List<Car> findCarsBy(final PaginationInfo paginationInfo, final String color) {
        return getCars(paginationInfo.getOffset(), color, paginationInfo.getLimit());
    }
    
    @Paginated (webLinking = false)
    public List<Car> findCarsByCustomHeaders(final PaginationInfo paginationInfo, final String color) {
        return getCars(paginationInfo.getOffset(), color, paginationInfo.getLimit());
    }
    
    private List<Car> getCars(final int offset, final String color, final int limit) {
        final EntityManager em = emf.createEntityManager();
        try {
            return em.createQuery("SELECT c FROM Car c WHERE c.color = :color", Car.class)
                    .setParameter("color", color)
                    .setFirstResult(offset)
                    .setMaxResults(limit)
                    .getResultList();
        } finally {
            em.close();
        }
    }

    //Only to demonstrate HSTS feature
    public List<String> mycars() {
        return Arrays.asList(new String[]{"Canyonero", "Bandit", "Truckasaurus", "Slackzda", "Lazyda"});
    }

    //Only to demonstrate Basic and Digest authentication
    public List<String> autobots() {
        return Arrays.asList(new String[]{"Brawn", "Bumblebee", "Cliffjumper", "Beachcomber", "Optimus Prime"});
    }
    
}
