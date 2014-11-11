package org.springframework.social.slideshare.api.impl.xml;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import org.springframework.social.slideshare.api.domain.Slideshow;

import java.util.List;

/**
 * @author Tadaya Tsuyukubo
 */
@JsonIgnoreProperties (ignoreUnknown = true)
public abstract class GetSlideshowsResponseMixin {

	@JacksonXmlProperty (localName = "Name")
	String name;
	@JacksonXmlProperty (localName = "Count")
	int count;
	@JacksonXmlProperty (localName = "Slideshow")
	@JacksonXmlElementWrapper (useWrapping = false)
	List<Slideshow> slideshows;

}
