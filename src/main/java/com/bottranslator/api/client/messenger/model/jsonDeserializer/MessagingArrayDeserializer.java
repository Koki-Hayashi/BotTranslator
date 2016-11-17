package com.bottranslator.api.client.messenger.model.jsonDeserializer;

import com.bottranslator.api.client.messenger.model.receive.Messaging;

public class MessagingArrayDeserializer extends OptionalArrayDeserializer<Messaging>
{
	protected MessagingArrayDeserializer()
	{
		super(Messaging.class);
	}
}