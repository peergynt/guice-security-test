package com.vaadin.guice.securitytest;

import javax.inject.Inject;
import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.annotations.Widgetset;
import com.vaadin.guice.annotation.Configuration;
import com.vaadin.guice.annotation.GuiceUI;
import com.vaadin.guice.server.GuiceVaadinServlet;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.Button;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

/**
 * This UI is the application entry point. A UI may either represent a browser window 
 * (or tab) or some part of a html page where a Vaadin application is embedded.
 * <p>
 * The UI is initialized using {@link #init(VaadinRequest)}. This method is intended to be 
 * overridden to add component to the user interface and initialize non-component functionality.
 */
@Theme("gstheme")
@Widgetset("com.vaadin.guice.securitytest.GuiceSecurityWidgetset")
@GuiceUI
public class GuiceSecurityUI extends UI {

    @Inject
    Button button;

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        final VerticalLayout layout = new VerticalLayout();

        final TextField name = new TextField();
        name.setCaption("Type your name here:");

        button.setCaption("Click Me");
        layout.addComponents(name, button);
        layout.setMargin(true);
        layout.setSpacing(true);

        setContent(layout);
    }

    @WebServlet(urlPatterns = { "/*", "/guice-security-test/*" }, name = "GuiceSecurityUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = GuiceSecurityUI.class, productionMode = false)
    @Configuration(modules={}, basePackages={"com.vaadin.guice.securitytest"})
    public static class GuiceSecurityUIServlet extends GuiceVaadinServlet {
    }
}
