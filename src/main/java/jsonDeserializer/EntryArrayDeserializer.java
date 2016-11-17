package jsonDeserializer;

import model.messenger.Entry;

public class EntryArrayDeserializer extends OptionalArrayDeserializer<Entry> {
	protected EntryArrayDeserializer() {
		super(Entry.class);
	}
}