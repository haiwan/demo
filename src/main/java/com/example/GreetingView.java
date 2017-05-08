package com.example;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.themes.ValoTheme;

@SpringView(name = GreetingView.VIEW_ID)
public class GreetingView extends CssLayout implements View{

	static final String VIEW_ID = "";

	public GreetingView(Greeting greeting){
		Label label = new Label(greeting.sayHi()+greeting.toString());
		label.addStyleName(ValoTheme.LABEL_H1);
		addComponent(label);
	}

	@Override
	public void enter(ViewChangeEvent viewChangeEvent) {
	}
}
