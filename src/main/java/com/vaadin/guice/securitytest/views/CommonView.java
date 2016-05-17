package com.vaadin.guice.securitytest.views;

import javax.inject.Inject;

import com.vaadin.guice.annotation.GuiceView;
import com.vaadin.guice.securitytest.Names;
import com.vaadin.ui.Label;

@GuiceView(name=Names.COMMON_VIEW)
public class CommonView extends User1And2And3View {

    private static final long serialVersionUID = 409945796920553295L;

    @Inject
    private Label commonLabel;

    @Override
    protected void init() {
        super.init();
        commonLabel.setValue("This label can be seen by all users");
        addComponent(commonLabel);
    }

}
