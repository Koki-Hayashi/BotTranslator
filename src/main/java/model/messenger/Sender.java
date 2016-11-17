package model.messenger;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by koki on 11/15/16.
 */
public class Sender
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
		return "Sender{" +
			"id='" + id + '\'' +
			'}';
	}
}
