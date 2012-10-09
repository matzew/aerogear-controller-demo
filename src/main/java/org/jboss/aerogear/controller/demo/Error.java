package org.jboss.aerogear.controller.demo;

public class Error {

    public void index(Exception e) {
        System.out.println("[Error] " + e);
    }

    public void security() {
    }

    public void throwException() {
        throw new RuntimeException("Demo Exception");
    }
}
