package com.bottranslator.api.client.messenger.model.send;

import java.util.Arrays;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Message
{
	@JsonProperty("text")
	private String text;

	@JsonProperty("quick_replies")
	private QuickReplies[] quickReplies;

	public String getText()
	{
		return text;
	}

	public void setText(String text)
	{
		this.text = text;
	}

	public QuickReplies[] getQuickReplies()
	{
		return quickReplies;
	}

	public void setQuickReplies(QuickReplies[] quickReplies)
	{
		this.quickReplies = quickReplies;
	}

	@Override
	public String toString()
	{
		return "Message{" +
			"text='" + text + '\'' +
			", quickReplies=" + Arrays.toString(quickReplies) +
			'}';
	}
}
