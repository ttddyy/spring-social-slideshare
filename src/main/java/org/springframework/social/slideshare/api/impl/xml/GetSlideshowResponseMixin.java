package org.springframework.social.slideshare.api.impl.xml;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import org.springframework.social.slideshare.api.domain.Slideshow;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Tadaya Tsuyukubo
 */
@JsonIgnoreProperties (ignoreUnknown = true)
//@JacksonXmlRootElement(localName = "Tag")
public abstract class GetSlideshowResponseMixin {

	@JacksonXmlProperty ( localName = "Name" )
	String name;
	@JacksonXmlProperty ( localName = "Count" )
	int count;
	@JacksonXmlProperty ( localName = "Slideshow" )
	@JacksonXmlElementWrapper(useWrapping = false)
	List<Slideshow> slideshows;
//	Slideshow[] slideshows;

//	@JacksonXmlProperty ( localName = "Slideshow" )
////	@JacksonXmlElementWrapper(localName = "Slideshow")
//	List<Slideshow> slideshows;

}
