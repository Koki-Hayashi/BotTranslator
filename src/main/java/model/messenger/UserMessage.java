package model.messenger;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import jsonDeserializer.EntryArrayDeserializer;

public class UserMessage
{
	@JsonDeserialize(using = EntryArrayDeserializer.class)
	@JsonProperty("entry")
	private List<Entry> entryList;

	@JsonProperty("object")
	private String object;

	public List<Entry> getEntryList()
	{
		return entryList;
	}

	public void setEntryList(List<Entry> entryList)
	{
		this.entryList = entryList;
	}

	public String getObject()
	{
		return object;
	}

	public void setObject(String object)
	{
		this.object = object;
	}

	@Override
	public String toString()
	{
		return "UserMessage{" +
			"entryList=" + entryList +
			", object='" + object + '\'' +
			'}';
	}
}
