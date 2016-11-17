package com.bottranslator.api.client.messenger.model.send;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Greeting
{
	public Greeting(String text)
	{
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
		return "Greeting{" +
			"text='" + text + '\'' +
			'}';
	}
}
