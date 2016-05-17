package com.vaadin.guice.securitytest.views;

import com.vaadin.guice.security.annotation.RestrictedVisibility;
import com.vaadin.guice.securitytest.Names;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

public class User1And2View extends VerticalLayout implements View {

    private static final long serialVersionUID = -5305037323392620039L;

    private boolean initDone = false;

    @RestrictedVisibility(value=Names.USER_1_NAME)
    private Label user1Label;

    @RestrictedVisibility(value=Names.USER_2_NAME)
    private Label user2Label;

    public User1And2View() {
        super();
    }

    protected void init() {
        user1Label.setValue("This label can only be seen by User 1");
        addComponent(user1Label);
        user2Label.setValue("This label can only be seen by User 2");
        addComponent(user2Label);
    }

    @Override
    public void enter(ViewChangeEvent event) {
        if (!initDone) {
            init();
            initDone = true;
        }
    }

}
