package com.example;

public class ChatMessageEvent {
	private String senderId;
	private String message;

	public ChatMessageEvent(String senderId, String message) {
		this.senderId = senderId;
		this.message = message;
	}

	public String getSenderId() {
		return senderId;
	}

	public String getMessage() {
		return message;
	}
}
