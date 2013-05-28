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

import org.jboss.aerogear.controller.demo.model.Car;

/**
 * <em>Home</em> is a pojo class which treats
 * requests of these application.
 * Each method must have an associated route
 * so that it can respond to a requisition.
 *
 * @see Routes
 */
public class Home {

    /**
     * Respond to a GET request to "/". The response page follow
     * a convention, having a <em>folder/jsp</em> at the directory <em>WEB-INF/pages</em>.
     * The folder/jsp are named after the business controller class/method been called, respectively.
     * Ex: <em>WEB-INF/pages/Home/index.jsp</em>
     *
     * @see Routes
     */
    public void index() {
        System.out.println("hello from controller");
    }

    /**
     * Respond to a GET request to "/delorean". The response page follow
     * a convention, having a <em>folder/jsp</em> at the directory <em>WEB-INF/pages</em>.
     * The folder/jsp are named after the business controller class/method been called, respectively.
     * Ex: <em>WEB-INF/pages/Home/anotherPage.jsp</em>
     * redirect the answer to <em>WEB-INF/pages/Home/anotherPage.jsp</em>.
     * The <em>Car<em> object create is exposed to the <em>anotherPage.jsp</em>
     * and might be accessed using Expression Language. The naming convention
     * used to expose the object is the class name in camel case. Ex: <pre>${car.brand}</pre>
     *
     * @return Car
     * @see Routes
     */
    public Car anotherPage() {
        return new Car("silver", "delorean");
    }

}
