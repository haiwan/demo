package com.example;

import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.spring.annotation.VaadinSessionScope;
import com.vaadin.spring.annotation.ViewScope;

import org.springframework.web.context.annotation.RequestScope;

@SpringComponent
@RequestScope
public class JavaCroGreeting implements Greeting{

	@Override
	public String sayHi() {
		return "Hello JavaCro!";
	}
}
