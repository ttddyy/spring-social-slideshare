package org.springframework.social.slideshare.api.domain;

/**
 * @author Tadaya Tsuyukubo
 */
public class SearchSlideshowResponse {
	private String query;
	private int resultOffset;  //  the offset of this result (if pages were used)
	private int numResults;  // number of results returned
	private int totalResults;  // total number of results

	private Slideshow[] slideshows;
}
