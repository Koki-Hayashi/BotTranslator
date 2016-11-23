package com.bottranslator.api.client.messenger.model.receive;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Postback
{
	@JsonProperty("payload")
	private String payload;

	@JsonProperty("referral")
	private Referral referral;

	public String getPayload()
	{
		return payload;
	}

	public void setPayload(String payload)
	{
		this.payload = payload;
	}

	public Referral getReferral()
	{
		return referral;
	}

	public void setReferral(Referral referral)
	{
		this.referral = referral;
	}
}
