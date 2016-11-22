package com.bottranslator.api.client.messenger.model.send;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CallToAction
{
	@JsonProperty("payload")
	private String payload;

	public CallToAction(String payload)
	{
		this.payload = payload;
	}

	public String getPayload()
	{
		return payload;
	}

	public void setPayload(String payload)
	{
		this.payload = payload;
	}

	@Override
	public String toString()
	{
		return "CallToAction{" +
			"payload='" + payload + '\'' +
			'}';
	}
}
