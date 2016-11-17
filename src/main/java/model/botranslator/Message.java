package model.botranslator;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by koki on 11/15/16.
 */
public class Message
{
	public Message(String text) {
		this.text = text;
	}

	@JsonProperty("text")
	private String text;

	public String getText()
	{
		return text;
	}

	public void setText(String text)
	{
		this.text = text;
	}

	@Override
	public String toString()
	{
		return "Message{" +
			"text='" + text + '\'' +
			'}';
	}
}
