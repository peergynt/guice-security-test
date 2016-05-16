package com.vaadin.guice.securitytest.views;

import com.vaadin.guice.annotation.GuiceView;
import com.vaadin.guice.securitytest.Names;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

@GuiceView(name=Names.ACCESS_DENIED_VIEW)
@com.vaadin.guice.security.annotation.AccessDeniedView
public class AccessDeniedView extends VerticalLayout implements View {

    private static final long serialVersionUID = 1687879102900955891L;

    public AccessDeniedView() {
        super();
        setSizeFull();
        Label label = new Label("Access Denied");
        label.addStyleName(ValoTheme.LABEL_BOLD);
        label.addStyleName(ValoTheme.LABEL_HUGE);
        addComponent(label);
    }

    @Override
    public void enter(ViewChangeEvent event) {
    }

}
