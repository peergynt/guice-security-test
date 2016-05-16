package com.vaadin.guice.securitytest.views;

import com.vaadin.guice.annotation.GuiceView;
import com.vaadin.guice.security.annotation.RestrictedTo;
import com.vaadin.guice.securitytest.Names;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

@GuiceView(name=Names.USER_2_VIEW)
@RestrictedTo(value=Names.USER_2_NAME)
public class User2View extends VerticalLayout implements View {

    private static final long serialVersionUID = -5119164727805267866L;

    public User2View() {
        super();
        setSizeFull();
        Label label = new Label("This the private page for user 2.");
        addComponent(label);
    }

    @Override
    public void enter(ViewChangeEvent event) {
    }

}
