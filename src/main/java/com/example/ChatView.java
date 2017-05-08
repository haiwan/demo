package com.example;

import javax.annotation.PostConstruct;

import com.vaadin.event.ShortcutAction.KeyCode;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.VaadinSession;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import org.vaadin.spring.events.EventBus;
import org.vaadin.spring.events.EventBusListenerMethodFilter;
import org.vaadin.spring.events.annotation.EventBusListenerMethod;

@SpringView(name = ChatView.VIEW_ID)
public class ChatView extends VerticalLayout implements View {
	static final String VIEW_ID = "chat";

	private EventBus.ApplicationEventBus eventBus;

	private VerticalLayout chatHistory;

	private String UUID = java.util.UUID.randomUUID().toString();

	public ChatView(EventBus.ApplicationEventBus eventBus) {
		this.eventBus = eventBus;
		this.eventBus.subscribe(this);
	}

	@PostConstruct
	private void init() {
		setSizeFull();
		Panel panel = new Panel();
		panel.setSizeFull();
		chatHistory = new VerticalLayout();
		panel.setContent(chatHistory);
		HorizontalLayout messageLayout = new HorizontalLayout();
		messageLayout.setWidth(100, Unit.PERCENTAGE);
		TextField messageField = new TextField();
		messageLayout.addComponentsAndExpand(messageField);
		Button send = new Button("Send", e -> {
			eventBus.publish(this,
					new ChatMessageEvent(UUID, messageField.getValue()));
			Label label = new Label(messageField.getValue());
			chatHistory.addComponent(label);
			chatHistory.setComponentAlignment(label, Alignment.MIDDLE_RIGHT);
			messageField.clear();
		});
		send.setClickShortcut(KeyCode.ENTER);
		messageLayout.addComponent(send);
		addComponentsAndExpand(panel);
		addComponent(messageLayout);
	}

	@EventBusListenerMethod()
	private void onMessageReceived(ChatMessageEvent event) {
		if(!event.getSenderId().equals(UUID)) {
			UI.getCurrent().access(() -> {
				Label inComingMessage = new Label();
				inComingMessage.setValue(event.getMessage());
				chatHistory.addComponent(inComingMessage);
			});
		}
	}

	@Override
	public void enter(ViewChangeEvent viewChangeEvent) {
		UI.getCurrent().setPollInterval(100);
	}

	@Override
	public void detach() {
		super.detach();
		UI.getCurrent().setPollInterval(-1);
	}

	public static class ChatEventBusListenerMethodFilter implements EventBusListenerMethodFilter {
		@Override
		public boolean filter(org.vaadin.spring.events.Event<?> event) {
			return true;
		}
	}
}
