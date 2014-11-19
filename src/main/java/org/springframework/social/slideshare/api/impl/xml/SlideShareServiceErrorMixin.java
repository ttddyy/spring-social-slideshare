package org.springframework.social.slideshare.api.impl.xml;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlText;

/**
 * @author Tadaya Tsuyukubo
 */
public class SlideShareServiceErrorMixin {

	public static class MessageMixin {
		@JacksonXmlProperty ( isAttribute = true, localName = "ID" )
		Integer id;

		@JacksonXmlText
		String value;
	}


	@JacksonXmlProperty ( localName = "Message" )
	SlideShareServiceError.Message message;
}
