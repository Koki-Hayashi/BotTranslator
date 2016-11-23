package com.bottranslator.api.client.messenger.model.receive;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Referral
{
	@JsonProperty("ref")
	private String ref;

	@JsonProperty("source")
	private String source;

	@JsonProperty("type")
	private String type;

	public String getRef()
	{
		return ref;
	}

	public void setRef(String ref)
	{
		this.ref = ref;
	}

	public String getSource()
	{
		return source;
	}

	public void setSource(String source)
	{
		this.source = source;
	}

	public String getType()
	{
		return type;
	}

	public void setType(String type)
	{
		this.type = type;
	}

	@Override
	public String toString()
	{
		return "Referral{" +
			"ref='" + ref + '\'' +
			", source='" + source + '\'' +
			", type='" + type + '\'' +
			'}';
	}
}
