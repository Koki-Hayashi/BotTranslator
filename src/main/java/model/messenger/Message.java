package model.messenger;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by koki on 11/15/16.
 */
public class Message
{
	@JsonProperty("mid")
	private String mid;

	@JsonProperty("text")
	private String text;

	@JsonProperty("seq")
	private int seq;

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
			"mid='" + mid + '\'' +
			", text='" + text + '\'' +
			", seq=" + seq +
			'}';
	}
}
