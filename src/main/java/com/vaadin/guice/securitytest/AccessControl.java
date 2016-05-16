package com.vaadin.guice.securitytest;

public interface AccessControl {
    String getCurrentUser();
    void setCurrentUser(String userName);
}
