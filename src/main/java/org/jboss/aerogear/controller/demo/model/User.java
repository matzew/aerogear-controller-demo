package org.jboss.aerogear.controller.demo.model;

import org.jboss.aerogear.security.model.AeroGearUser;

public class User extends AeroGearUser {

    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;

    public User(){
    }

    public User(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFullName() {
        return String.format("%s %s", firstName, lastName);
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getKey() {
        return String.format("%s%s", KEY_PREFIX, id);
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public void setPassword(String password) {
       this.password = password;
    }
}
