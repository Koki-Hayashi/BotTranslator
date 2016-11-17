package com.bottranslator.api.client.messenger.model.send;

import com.fasterxml.jackson.annotation.JsonProperty;

public class GreetingRegister
{
	@JsonProperty("setting_type")
	private String setting_type = "greeting";

	@JsonProperty("greeting")
	private Greeting greeting;

	public GreetingRegister(Greeting greeting)
	{
		this.greeting = greeting;
	}

	public String getSetting_type()
	{
		return setting_type;
	}

	public void setSetting_type(String setting_type)
	{
		this.setting_type = setting_type;
	}

	public Greeting getGreeting()
	{
		return greeting;
	}

	public void setGreeting(Greeting greeting)
	{
		this.greeting = greeting;
	}

	@Override
	public String toString()
	{
		return "GreetingRegister{" +
			"setting_type='" + setting_type + '\'' +
			", greeting=" + greeting +
			'}';
	}
}
