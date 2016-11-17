package com.bottranslator.api.client.messenger.model.send;

import com.fasterxml.jackson.annotation.JsonProperty;

public class QuickReplies
{
	@JsonProperty("content_type")
	private String contentType;

	@JsonProperty("title")
	private String title;

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

	@Override
	public String toString()
	{
		return "QuickReplies{" +
			"contentType='" + contentType + '\'' +
			", title='" + title + '\'' +
			'}';
	}
}
