package com.bottranslator.api.client.messenger.model.receive;

import com.fasterxml.jackson.annotation.JsonProperty;

public class QuickReply
{
	@JsonProperty("payload")
	private String payload;

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
		return "QuickReply{" +
			"payload='" + payload + '\'' +
			'}';
	}
}
