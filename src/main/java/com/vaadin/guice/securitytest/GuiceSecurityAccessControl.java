package com.vaadin.guice.securitytest;

import com.vaadin.server.VaadinSession;

public class GuiceSecurityAccessControl implements AccessControl {

    private final String USER_NAME_ATTRIBUTE = "userName";

    // Returns 'null' if no user is 'logged in'
    public String getCurrentUser() {
        VaadinSession session = VaadinSession.getCurrent();
        if (session == null) {
            throw new IllegalStateException("No vaadin sessions");
        }
        return (String) session.getAttribute(USER_NAME_ATTRIBUTE);
    }

    public void setCurrentUser(String userName) {
        VaadinSession session = VaadinSession.getCurrent();
        if (session == null) {
            throw new IllegalStateException("No vaadin sessions");
        }
        session.setAttribute(USER_NAME_ATTRIBUTE, userName);
    }

}
