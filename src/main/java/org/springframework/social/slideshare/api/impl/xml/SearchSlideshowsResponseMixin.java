package org.springframework.social.slideshare.api.impl.xml;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import org.springframework.social.slideshare.api.domain.SearchSlideshowsResponse;
import org.springframework.social.slideshare.api.domain.Slideshow;

import java.util.List;

/**
 * @author Tadaya Tsuyukubo
 */
@JsonIgnoreProperties ( ignoreUnknown = true )
public class SearchSlideshowsResponseMixin {

	@JsonIgnoreProperties ( ignoreUnknown = true )
	public static abstract class MetaInfoMixin {
		@JacksonXmlProperty ( localName = "Query" )
		String query;
		@JacksonXmlProperty ( localName = "ResultOffset" )
		int resultOffset;
		@JacksonXmlProperty ( localName = "NumResults" )
		int numResults;
		@JacksonXmlProperty ( localName = "TotalResults" )
		int totalResults;
	}

	@JacksonXmlElementWrapper ( localName = "Meta" )
	SearchSlideshowsResponse.MetaInfo metaInfo;

	@JacksonXmlProperty ( localName = "Slideshow" )
	@JacksonXmlElementWrapper ( useWrapping = false )
	List<Slideshow> slideshows;
}
