package com.github.dojotags.json;

import java.io.IOException;

import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.JsonSerializer;
import org.codehaus.jackson.map.SerializerProvider;

/**
 * Custom Json serializer for {@code Items} bean. Serializes {@code list}
 * property.
 * 
 * @author gushakov
 * 
 */
public class ItemsSerializer extends JsonSerializer<Items> {

	@Override
	public void serialize(Items value, JsonGenerator jgen,
			SerializerProvider provider) throws IOException,
			JsonProcessingException {
		jgen.writeObject(value.getList());
	}

}
