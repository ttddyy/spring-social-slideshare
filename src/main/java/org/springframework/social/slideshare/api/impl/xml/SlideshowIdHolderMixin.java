package org.springframework.social.slideshare.api.impl.xml;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

/**
 * @author Tadaya Tsuyukubo
 */
@JsonIgnoreProperties ( ignoreUnknown = true )
public class SlideshowIdHolderMixin {

	@JacksonXmlProperty ( localName = "SlideShowID" )
	String id;

}
