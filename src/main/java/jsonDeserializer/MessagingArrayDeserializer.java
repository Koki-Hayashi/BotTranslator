package jsonDeserializer;

import model.messenger.Messaging;

public class MessagingArrayDeserializer extends OptionalArrayDeserializer<Messaging>
{
	protected MessagingArrayDeserializer()
	{
		super(Messaging.class);
	}
}