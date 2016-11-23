package com.bottranslator.data.cache.model;

import com.bottranslator.api.client.ms.LANGUAGE;

public class LanguageSetting
{
	private LANGUAGE from;
	private LANGUAGE to;

	public LanguageSetting(LANGUAGE from, LANGUAGE to)
	{
		this.from = from;
		this.to = to;
	}

	public LANGUAGE getFrom()
	{
		return from;
	}

	public void setFrom(LANGUAGE from)
	{
		this.from = from;
	}

	public LANGUAGE getTo()
	{
		return to;
	}

	public void setTo(LANGUAGE to)
	{
		this.to = to;
	}

	@Override
	public String toString()
	{
		return "LanguageSetting{" +
			"from=" + from +
			", to=" + to +
			'}';
	}
}
