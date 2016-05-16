package com.vaadin.guice.securitytest;

import org.reflections.Reflections;

import com.vaadin.guice.security.SecurityModule;
import com.vaadin.guice.security.api.PathAccessEvaluator;

public class GuiceSecurityModule extends SecurityModule {

    public GuiceSecurityModule(Reflections reflections) {
        super(reflections);
    }

    @Override
    protected Class<? extends PathAccessEvaluator> getPathAccessEvaluatorClass() {
        return GuiceSecurityPathAccess.class;
    }

    @Override
    protected void configure() {
        super.configure();
        bind(AccessControl.class).to(GuiceSecurityAccessControl.class);
    }

}
