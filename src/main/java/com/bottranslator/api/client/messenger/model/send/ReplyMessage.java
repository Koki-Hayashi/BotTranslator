package com.bottranslator.api.client.messenger.model.send;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ReplyMessage {

	public ReplyMessage(Recipient recipient, Message message)
	{
		this.recipient = recipient;
		this.message = message;
	}

	@JsonProperty("recipient")
	private Recipient recipient;

	@JsonProperty("message")
	private Message message;

	public Recipient getRecipient()
	{
		return recipient;
	}

	public void setRecipient(Recipient recipient)
	{
		this.recipient = recipient;
	}

	public Message getMessage()
	{
		return message;
	}

	public void setMessage(Message message)
	{
		this.message = message;
	}

	@Override
	public String toString()
	{
		return "ReplyMessage{" +
			"recipient=" + recipient +
			", message=" + message +
			'}';
	}
}
