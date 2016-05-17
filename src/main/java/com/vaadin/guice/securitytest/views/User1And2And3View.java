package com.vaadin.guice.securitytest.views;

import com.vaadin.guice.security.annotation.RestrictedVisibility;
import com.vaadin.guice.securitytest.Names;
import com.vaadin.ui.Label;

public class User1And2And3View extends User1And2View {

    private static final long serialVersionUID = 7825213373594247623L;

    @RestrictedVisibility(value=Names.USER_3_NAME)
    private Label user3Label;

    @Override
    protected void init() {
        super.init();
        user3Label.setValue("This label can only be seen by User 3");
        addComponent(user3Label);
    }

}
