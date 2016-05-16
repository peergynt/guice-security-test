package com.vaadin.guice.securitytest;

import javax.inject.Inject;
import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.annotations.Widgetset;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.guice.annotation.Configuration;
import com.vaadin.guice.annotation.GuiceUI;
import com.vaadin.guice.annotation.ViewContainer;
import com.vaadin.guice.security.annotation.RestrictedVisibility;
import com.vaadin.guice.security.api.VisibilityManager;
import com.vaadin.guice.server.GuiceVaadinServlet;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.themes.ValoTheme;

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

    private static final long serialVersionUID = -7357009768687504080L;

    @Inject
    private AccessControl accessControl;

    @Inject
    private VisibilityManager visibilityManager;

    @Inject
    private Button publicButton;

    @RestrictedVisibility(value=Names.USER_1_NAME)
    private Button user1Button;

    @RestrictedVisibility(value=Names.USER_2_NAME)
    private Button user2Button;

    @Inject
    private Button invalidButton;

    @Inject
    @ViewContainer
    private VerticalLayout contentLayout;

    private Label userName;

    @SuppressWarnings("serial")
    private ClickListener menuButtonClickListener = new ClickListener() {
        @Override
        public void buttonClick(ClickEvent event) {
            String viewName = (String) event.getButton().getData();
            if (viewName != null) {
                getNavigator().navigateTo(viewName);
            }
        }
    };

    @SuppressWarnings("serial")
    @Override
    protected void init(VaadinRequest vaadinRequest) {

        VerticalLayout menuLayout = new VerticalLayout();
        menuLayout.setSpacing(true);
        menuLayout.setWidthUndefined();
        menuLayout.setPrimaryStyleName(ValoTheme.MENU_ROOT);

        Label title = new Label("Guice Security Test UI");
        title.addStyleName(ValoTheme.MENU_TITLE);
        title.setHeight("60px");
        menuLayout.addComponent(title);

        user1Button.setCaption("User 1 Stuff");
        user1Button.setData(Names.USER_1_VIEW);
        user1Button.setPrimaryStyleName(ValoTheme.MENU_ITEM);
        user1Button.addClickListener(menuButtonClickListener);

        user2Button.setCaption("User 2 Stuff");
        user2Button.setData(Names.USER_2_VIEW);
        user2Button.setPrimaryStyleName(ValoTheme.MENU_ITEM);
        user2Button.addClickListener(menuButtonClickListener);

        publicButton.setCaption("Public Stuff");
        publicButton.setData(Names.PUBLIC_VIEW);
        publicButton.setPrimaryStyleName(ValoTheme.MENU_ITEM);
        publicButton.addClickListener(menuButtonClickListener);

        invalidButton.setCaption("Invalid Stuff");
        invalidButton.setData("invalid");
        invalidButton.setPrimaryStyleName(ValoTheme.MENU_ITEM);
        invalidButton.addClickListener(menuButtonClickListener);

        menuLayout.addComponents(user1Button, user2Button, publicButton, invalidButton);

        contentLayout.setMargin(true);
        contentLayout.setSpacing(true);
        contentLayout.setSizeFull();

        HorizontalLayout topBarLayout = new HorizontalLayout();
        topBarLayout.setPrimaryStyleName(ValoTheme.MENU_TITLE);
        topBarLayout.setMargin(false);
        topBarLayout.setSpacing(true);
        topBarLayout.setHeight("60px");
        topBarLayout.setWidth("100%");

        Label loggedIn = new Label("Viewing as user:");
        loggedIn.setWidthUndefined();

        userName = new Label();
        userName.setWidth("300px");

        ComboBox userField = new ComboBox();
        userField.setInvalidAllowed(false);
        userField.setNullSelectionAllowed(true);
        userField.addItem(Names.USER_1_NAME);
        userField.addItem(Names.USER_2_NAME);
        userField.addItem(Names.USER_3_NAME);
        userField.addValueChangeListener(new ValueChangeListener() {
            @Override
            public void valueChange(ValueChangeEvent event) {
                Object value = event.getProperty().getValue();
                String name = (value instanceof String) ? (String) value : null;
                accessControl.setCurrentUser(name);
                userName.setValue(name);

                visibilityManager.evaluateVisibility();
            }
        });
        userField.setValue(accessControl.getCurrentUser());

        Label gap = new Label();
        topBarLayout.addComponents(loggedIn, userName, userField, gap);
        topBarLayout.setExpandRatio(gap, 1.0f);

        VerticalLayout rightLayout = new VerticalLayout();
        rightLayout.setMargin(false);
        rightLayout.setSpacing(false);
        rightLayout.setSizeFull();

        rightLayout.addComponents(topBarLayout, contentLayout);
        rightLayout.setExpandRatio(contentLayout, 1.0f);

        HorizontalLayout mainLayout = new HorizontalLayout();
        mainLayout.setSizeFull();
        mainLayout.setMargin(false);
        mainLayout.setSpacing(false);
        mainLayout.addComponent(menuLayout);
        mainLayout.addComponent(rightLayout);

        mainLayout.setExpandRatio(rightLayout, 1.0f);

        setContent(mainLayout);
    }

    @WebServlet(urlPatterns = { "/*", "/guice-security-test/*" }, name = "GuiceSecurityUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = GuiceSecurityUI.class, productionMode = false)
    @Configuration(modules={GuiceSecurityModule.class}, basePackages={"com.vaadin.guice.securitytest"})
    public static class GuiceSecurityUIServlet extends GuiceVaadinServlet {
        private static final long serialVersionUID = 8605640975008063730L;
    }

}
