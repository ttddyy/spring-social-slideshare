package org.springframework.social.slideshare.api.domain;

import java.util.List;

/**
 * For get slideshows by tag, group, user.
 *
 * @author Tadaya Tsuyukubo
 */
public class GetSlideshowsResponse {

	public static enum RequestType {
		BY_TAG,
		BY_GROUP,
		BY_USER
	}

	private RequestType requestType;
	private String name;
	private int count;
	private List<Slideshow> slideshows;

	public RequestType getRequestType() {
		return requestType;
	}

	public void setRequestType(RequestType requestType) {
		this.requestType = requestType;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public List<Slideshow> getSlideshows() {
		return slideshows;
	}

	public void setSlideshows(List<Slideshow> slideshows) {
		this.slideshows = slideshows;
	}

}
