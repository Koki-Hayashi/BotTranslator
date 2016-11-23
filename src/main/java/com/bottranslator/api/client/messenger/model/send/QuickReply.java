package com.bottranslator.api.client.messenger.model.send;

import com.fasterxml.jackson.annotation.JsonProperty;

public class QuickReply
{
	@JsonProperty("content_type")
	private String contentType;

	@JsonProperty("title")
	private String title;

	@JsonProperty("payload")
	private String payload;

	public String getContentType()
	{
		return contentType;
	}

	public void setContentType(String contentType)
	{
		this.contentType = contentType;
	}

	public String getTitle()
	{
		return title;
	}

	public void setTitle(String title)
	{
		this.title = title;
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
		return "QuickReply{" +
			"contentType='" + contentType + '\'' +
			", title='" + title + '\'' +
			", payload='" + payload + '\'' +
			'}';
	}
}
