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
package org.jboss.aerogear.controller.demo.model;

import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonProperty;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

/**
 * The model car is an immutable object that represents our domain.
 */
@Entity
public class Car {
    
    @Id
    @SequenceGenerator( name = "seq", sequenceName = "CAR_SEQ", allocationSize = 1, initialValue = 1 )
    @GeneratedValue( strategy = GenerationType.SEQUENCE, generator = "seq" )
    private Long id;
    
    @Column
    private String color;
    
    @Column
    private String brand;
    
    public Car() {
    }

    @JsonCreator
    public Car(@JsonProperty("color") final String color, @JsonProperty("brand") final String brand) {
        this.color = color;
        this.brand = brand;
    }

    public String getColor() {
        return color;
    }

    public String getBrand() {
        return brand;
    }

    @Override
    public String toString() {
        return "Car[id='" + id + '\'' + "color='" + color + '\'' + ", brand='" + brand + '\'' + ']';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }
    
}
