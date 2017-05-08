package com.example;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Button;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

@SpringView(name = GreetingView2.VIEW_ID)
public class GreetingView2 extends VerticalLayout implements View {
	static final String VIEW_ID = "greeting2";

	public GreetingView2(Greeting greeting){
		Label label = new Label();
		label.addStyleName(ValoTheme.LABEL_H1);

		Button sayHiBtn = new Button("Say Hi", e->{
			label.setValue(greeting.sayHi()+greeting.toString());
		});
		sayHiBtn.addStyleName(ValoTheme.BUTTON_LARGE);

		addComponents(sayHiBtn, label);
	}

	@Override
	public void enter(ViewChangeEvent viewChangeEvent) {

	}
}
