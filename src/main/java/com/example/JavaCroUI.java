package com.example;

import com.vaadin.annotations.Push;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewDisplay;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinSession;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.spring.annotation.SpringViewDisplay;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

import org.springframework.beans.factory.annotation.Autowired;

@SpringUI
public class JavaCroUI extends UI{

	private final ViewDisplay viewDisplay;

	@Autowired
	public JavaCroUI(ViewDisplay viewDisplay){
		this.viewDisplay = viewDisplay;
	}

	@Override
	protected void init(VaadinRequest vaadinRequest) {
		HorizontalLayout rootLayout = new HorizontalLayout();
		rootLayout.setSizeFull();
		Component navigationBar = createNavigationBar();
		rootLayout.addComponents(navigationBar, (Component)viewDisplay);
		rootLayout.setExpandRatio(navigationBar, 2);
		rootLayout.setExpandRatio((Component)viewDisplay, 8);
		setContent(rootLayout);
	}

	private Component createNavigationBar() {
		VerticalLayout navigationBar = new VerticalLayout();
		navigationBar.setDefaultComponentAlignment(Alignment.TOP_CENTER);
		navigationBar.addComponent(createNavigationButton("Greeting", GreetingView.VIEW_ID));
		navigationBar.addComponent(createNavigationButton("Greeting2", GreetingView2.VIEW_ID));
		navigationBar.addComponent(createNavigationButton("Chat", ChatView.VIEW_ID));
		Button logout = createLogoutButton();
		navigationBar.addComponent(logout);
		navigationBar.setExpandRatio(logout, 1);
		navigationBar.setComponentAlignment(logout, Alignment.BOTTOM_CENTER);
		navigationBar.setSizeFull();
		return navigationBar;
	}

	private Button createLogoutButton() {
		Button logout = new Button("Logout", e->{
			VaadinSession.getCurrent().close();
			setContent(new Label("You've signed out"));
		});
		logout.setIcon(VaadinIcons.SIGN_OUT);
		logout.addStyleName(ValoTheme.BUTTON_LARGE);
		return logout;
	}

	private Component createNavigationButton(String caption, String viewId){
		Button button = new Button(caption, e->navigateTo(viewId));
		button.setStyleName(ValoTheme.BUTTON_LINK);
		button.addStyleName(ValoTheme.BUTTON_HUGE);

		return button;
	}

	private void navigateTo(String viewId) {
		getNavigator().navigateTo(viewId);
	}
}
