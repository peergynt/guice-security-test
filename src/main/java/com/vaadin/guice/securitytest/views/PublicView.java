package com.vaadin.guice.securitytest.views;

import com.vaadin.guice.annotation.GuiceView;
import com.vaadin.guice.securitytest.Names;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

@GuiceView(name=Names.PUBLIC_VIEW)
public class PublicView extends VerticalLayout implements View {

    private static final long serialVersionUID = -1019492392653987991L;

    public PublicView() {
        super();
        setSizeFull();
        Label label = new Label("This is a page that anyone can view.");
        addComponent(label);
    }

    @Override
    public void enter(ViewChangeEvent event) {
    }

}
