package com.bottranslator.api.client.messenger.model.receive;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Recipient
{
	@JsonProperty("id")
	private String id;

	public String getId()
	{
		return id;
	}

	public void setId(String id)
	{
		this.id = id;
	}

	@Override
	public String toString()
	{
		return "Recipient{" +
			"id='" + id + '\'' +
			'}';
	}
}
