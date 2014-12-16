package org.springframework.social.slideshare.api.impl.xml;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.deser.ValueInstantiator;
import com.fasterxml.jackson.databind.deser.impl.CreatorCollector;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.social.slideshare.api.domain.GetSlideshowsResponse;
import org.springframework.social.slideshare.api.domain.SearchSlideshowsResponse;
import org.springframework.social.slideshare.api.domain.Slideshow;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.text.SimpleDateFormat;

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
		return new Jackson2ObjectMapperBuilder()
				// mixins
				.mixIn(Slideshow.class, SlideshowMixIn.class)
				.mixIn(Slideshow.Tag.class, SlideshowMixIn.TagMixin.class)
				.mixIn(Slideshow.RelatedSlideshow.class, SlideshowMixIn.RelatedSlideshowMixin.class)

				.mixIn(GetSlideshowsResponse.class, GetSlideshowsResponseMixin.class)
				.mixIn(SearchSlideshowsResponse.class, SearchSlideshowsResponseMixin.class)
				.mixIn(SearchSlideshowsResponse.MetaInfo.class, SearchSlideshowsResponseMixin.MetaInfoMixin.class)
				.mixIn(SlideshowIdHolder.class, SlideshowIdHolderMixin.class)

				// errors
				.mixIn(SlideShareServiceError.class, SlideShareServiceErrorMixin.class)
				.mixIn(SlideShareServiceError.Message.class, SlideShareServiceErrorMixin.MessageMixin.class)

				.dateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss zzz"))
				.createXmlMapper(true)
				.build();
	}

	public static <T> T deserializeNodeForCollection(JsonParser jp, DeserializationContext ctxt, JavaType javaType) throws IOException, JsonProcessingException {

		if (jp.isExpectedStartArrayToken()) {
			JsonNode jsonNode = jp.readValueAs(JsonNode.class);
			T result = XML_MAPPER.reader(javaType).readValue(jsonNode);
			return result;
		}
		else if (jp.getCurrentToken() == JsonToken.VALUE_STRING) {
			String str = jp.getText();
			if (StringUtils.isEmpty(StringUtils.trimAllWhitespace(str))) {
				// TODO: cleanup
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
