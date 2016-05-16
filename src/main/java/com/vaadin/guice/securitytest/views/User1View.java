package com.vaadin.guice.securitytest.views;

import com.vaadin.guice.annotation.GuiceView;
import com.vaadin.guice.security.annotation.RestrictedTo;
import com.vaadin.guice.securitytest.Names;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

@GuiceView(name=Names.USER_1_VIEW)
@RestrictedTo(value=Names.USER_1_NAME)
public class User1View extends VerticalLayout implements View {

    private static final long serialVersionUID = -9177841358428567347L;

    public User1View() {
        super();
        setSizeFull();
        Label label = new Label("This the private page for user 1.");
        addComponent(label);
    }

    @Override
    public void enter(ViewChangeEvent event) {
    }

}
