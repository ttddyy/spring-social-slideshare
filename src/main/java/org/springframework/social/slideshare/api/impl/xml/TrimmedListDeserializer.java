package org.springframework.social.slideshare.api.impl.xml;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.type.TypeFactory;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import static org.springframework.social.slideshare.api.impl.xml.JacksonUtils.deserializeNodeForCollection;

/**
 * @author Tadaya Tsuyukubo
 */
public abstract class TrimmedListDeserializer<T> extends JsonDeserializer<List<T>> {

	//  Use TypeReference if this class needs to handle other than list

	/**
	 * Child class to return class of T.
	 *
	 * @return class of T
	 */
	public abstract Class<T> getElementClass();

	public List<T> deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException, JsonProcessingException {

		TypeFactory typeFactory = JacksonUtils.XML_MAPPER.getTypeFactory();
		JavaType javaType = typeFactory.constructCollectionType(List.class, getElementClass());
		return deserializeNodeForCollection(jp, ctxt, javaType);
	}

	@Override
	public List<T> getNullValue() {
		return Collections.emptyList();
	}


}
