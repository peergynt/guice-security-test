package com.vaadin.guice.securitytest;

import javax.inject.Inject;

import com.vaadin.guice.security.api.PathAccessEvaluator;

public class GuiceSecurityPathAccess implements PathAccessEvaluator {

    @Inject
    private AccessControl accessControl;

    @Override
    public boolean isGranted(String permission) {
        String currentUser = accessControl.getCurrentUser();
        if (permission.equals(currentUser)) {
            return true;
        }
        return false;
    }

}
