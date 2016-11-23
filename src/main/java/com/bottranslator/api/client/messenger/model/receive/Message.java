package com.bottranslator.api.client.messenger.model.receive;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Message
{
	@JsonProperty("quick_reply")
	private QuickReply quickReply;

	@JsonProperty("mid")
	private String mid;

	@JsonProperty("text")
	private String text;

	@JsonProperty("seq")
	private int seq;

	public QuickReply getQuickReply()
	{
		return quickReply;
	}

	public void setQuickReply(QuickReply quickReply)
	{
		this.quickReply = quickReply;
	}

	public String getMid()
	{
		return mid;
	}

	public void setMid(String mid)
	{
		this.mid = mid;
	}

	public String getText()
	{
		return text;
	}

	public void setText(String text)
	{
		this.text = text;
	}

	public int getSeq()
	{
		return seq;
	}

	public void setSeq(int seq)
	{
		this.seq = seq;
	}

	@Override
	public String toString()
	{
		return "Message{" +
			"quickReply=" + quickReply +
			", mid='" + mid + '\'' +
			", text='" + text + '\'' +
			", seq=" + seq +
			'}';
	}
}
