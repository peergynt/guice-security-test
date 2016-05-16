package com.vaadin.guice.securitytest.views;

import com.vaadin.guice.annotation.GuiceView;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

@GuiceView(isErrorView=true)
public class ErrorView extends VerticalLayout implements View {

    private static final long serialVersionUID = -1062598961115967647L;

    private Label label;

    public ErrorView() {
        super();
        setSizeFull();
        label = new Label();
        label.addStyleName(ValoTheme.LABEL_FAILURE);
        label.addStyleName(ValoTheme.LABEL_HUGE);
        addComponent(label);
    }

    @Override
    public void enter(ViewChangeEvent event) {
        String errorMessage = "Error: '" + event.getViewName() + "' is not a valid view name!";
        label.setValue(errorMessage);
    }

}
