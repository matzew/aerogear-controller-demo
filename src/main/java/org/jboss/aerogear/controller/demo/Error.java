package org.jboss.aerogear.controller.demo;

public class Error {

    public String index(Exception e) {
        System.out.println("[Error] " + e);
        return "{exception:" + e.getMessage() + "}";
    }

    public void security() {
    }

    public void throwException() {
        throw new RuntimeException("Demo Exception");
    }
}
