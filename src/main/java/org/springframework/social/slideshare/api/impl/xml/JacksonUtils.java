package org.springframework.social.slideshare.api.impl.xml;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.deser.ValueInstantiator;
import com.fasterxml.jackson.databind.deser.impl.CreatorCollector;
import com.fasterxml.jackson.dataformat.xml.JacksonXmlModule;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.social.slideshare.api.domain.GetSlideshowsResponse;
import org.springframework.social.slideshare.api.domain.Slideshow;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Tadaya Tsuyukubo
 */
public class JacksonUtils {

	// shared XmlMapper
	public static XmlMapper XML_MAPPER;

	static {
		XML_MAPPER = createXmlMapper();
	}

	private static XmlMapper createXmlMapper() {
		List<Module> modules = new ArrayList<>();
		modules.add(new JacksonXmlModule() {
			@Override
			public void setupModule(SetupContext context) {
				super.setupModule(context);
				context.setMixInAnnotations(Slideshow.class, SlideshowMixIn.class);
				context.setMixInAnnotations(Slideshow.Tag.class, SlideshowMixIn.TagMixin.class);
				context.setMixInAnnotations(Slideshow.RelatedSlideshow.class, SlideshowMixIn.RelatedSlideshowMixin.class);

				context.setMixInAnnotations(GetSlideshowsResponse.class, GetSlideshowsResponseMixin.class);
			}
		});

		return new Jackson2ObjectMapperBuilder()
				.modules(modules)
				.dateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss zzz"))
//				.featuresToEnable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT)  // TODO: filter null element in collection
//				.featuresToEnable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
//				.featuresToEnable(DeserializationFeature.USE_JAVA_ARRAY_FOR_JSON_ARRAY)
				.createXmlMapper(true)
				.build();
	}

	public static <T> T deserializeNodeForCollection(JsonParser jp, DeserializationContext ctxt, JavaType javaType) throws IOException, JsonProcessingException {

		if (jp.isExpectedStartArrayToken()) {
			JsonNode jsonNode = jp.readValueAs(JsonNode.class);
			T result = XML_MAPPER.reader(javaType).readValue(jsonNode);
			return result;
		}
		else if (jp.getCurrentToken() == JsonToken.VALUE_STRING) {  // TODO: add comment
			String str = jp.getText();
			if (StringUtils.isEmpty(StringUtils.trimAllWhitespace(str))) {
				// TOOD: cleanup
				final DeserializationConfig config = ctxt.getConfig();
				BeanDescription beanDesc = config.introspect(javaType);
				boolean fixAccess = ctxt.canOverrideAccessModifiers();
				CreatorCollector creators = new CreatorCollector(beanDesc, fixAccess);
				ValueInstantiator valueInstantiator = creators.constructValueInstantiator(config);
				return (T) valueInstantiator.createUsingDefault(ctxt);
			}
		}

		throw ctxt.mappingException("Expected XML element");
	}

}
