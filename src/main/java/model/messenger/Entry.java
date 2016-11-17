package model.messenger;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import jsonDeserializer.MessagingArrayDeserializer;

/**
 * Created by koki on 11/15/16.
 */
public class Entry
{
	@JsonProperty("id")
	private String id;

	@JsonProperty("time")
	private Date time;

	@JsonDeserialize(using = MessagingArrayDeserializer.class)
	@JsonProperty("messaging")
	private List<Messaging> messagingList;

	public String getId()
	{
		return id;
	}

	public void setId(String id)
	{
		this.id = id;
	}

	public Date getTime()
	{
		return time;
	}

	public void setTime(Date time)
	{
		this.time = time;
	}

	public List<Messaging> getMessagingList()
	{
		return messagingList;
	}

	public void setMessagingList(List<Messaging> messagingList)
	{
		this.messagingList = messagingList;
	}

	@Override
	public String toString()
	{
		return "Entry{" +
			"id='" + id + '\'' +
			", time=" + time +
			", messagingList=" + messagingList +
			'}';
	}
}
