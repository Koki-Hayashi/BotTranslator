package model.messenger;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by koki on 11/15/16.
 */
public class Messaging
{
	@JsonProperty("sender")
	private Sender sender;

	@JsonProperty("recipient")
	private Recipient recipient;

	@JsonProperty("message")
	private Message message;

	@JsonProperty("timestamp")
	private Date timestamp;

	public Sender getSender()
	{
		return sender;
	}

	public void setSender(Sender sender)
	{
		this.sender = sender;
	}

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

	public Date getTimestamp()
	{
		return timestamp;
	}

	public void setTimestamp(Date timestamp)
	{
		this.timestamp = timestamp;
	}

	@Override
	public String toString()
	{
		return "Messaging{" +
			"sender=" + sender +
			", recipient=" + recipient +
			", message=" + message +
			", timestamp=" + timestamp +
			'}';
	}
}
