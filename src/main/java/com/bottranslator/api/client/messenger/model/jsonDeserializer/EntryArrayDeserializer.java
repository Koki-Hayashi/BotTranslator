package com.bottranslator.api.client.messenger.model.jsonDeserializer;

import com.bottranslator.api.client.messenger.model.receive.Entry;

public class EntryArrayDeserializer extends OptionalArrayDeserializer<Entry>
{
	protected EntryArrayDeserializer()
	{
		super(Entry.class);
	}
}