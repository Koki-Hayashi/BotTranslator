package com.bottranslator.api.client.messenger.model.jsonDeserializer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;

public abstract class OptionalArrayDeserializer<T> extends JsonDeserializer<List<T>>
{

	private final Class<T> clazz;

	public OptionalArrayDeserializer(Class<T> clazz)
	{
		this.clazz = clazz;
	}

	@Override
	public List<T> deserialize(JsonParser jp, DeserializationContext ctx) throws IOException
	{
		ObjectCodec oc = jp.getCodec();
		JsonNode node = oc.readTree(jp);
		ArrayList<T> list = new ArrayList<>();
		if (node.isArray())
		{
			for (JsonNode elementNode : node)
			{
				list.add(oc.treeToValue(elementNode, clazz));
			}
		}
		else
		{
			list.add(oc.treeToValue(node, clazz));
		}
		return list;
	}
}