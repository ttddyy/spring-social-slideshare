package org.springframework.social.slideshare.api.domain;

/**
 * For get slideshows by tag, group, user.
 *
 * @author Tadaya Tsuyukubo
 */
public class GetSlideshowResponse {

	public static enum RequestType {
		BY_TAG,
		BY_GROUP,
		BY_USER
	}

	private RequestType requestType;
	private String name;
	private int count;
	private Slideshow[] slideshows;
}
