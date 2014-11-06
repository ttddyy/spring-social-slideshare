package org.springframework.social.slideshare.api.impl;

import org.springframework.social.slideshare.api.SlideshowOperations;
import org.springframework.social.slideshare.api.domain.Slideshow;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestOperations;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;


/**
 * @author Tadaya Tsuyukubo
 */
public class SlideshowTemplate implements SlideshowOperations {
	public static final String BASE_URL = "https://www.slideshare.net/api/2/";
	public static final String GET_SLIDESHOW_URL = BASE_URL + "get_slideshow";
	public static final String GET_SLIDESHOW_BY_TAG_URL = BASE_URL + "get_slideshows_by_tag";
	public static final String GET_SLIDESHOW_BY_GROUP_URL = BASE_URL + "get_slideshows_by_group";
	public static final String GET_SLIDESHOW_BY_USER_URL = BASE_URL + "get_slideshows_by_user";

	private final RestOperations restOperations;

	public SlideshowTemplate(RestOperations restOperations) {
		this.restOperations = restOperations;
	}

	public Slideshow getSlideshow(String slideshowId, String slideshowUrl, String username, String password, boolean excludeTags, boolean detailed) {
		if (StringUtils.isEmpty(slideshowId) && StringUtils.isEmpty(slideshowUrl)) {
			throw new SlideShareException("slideshowId and slideshowUrl are empty");
		}

		UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(GET_SLIDESHOW_URL);

		if (StringUtils.hasLength(slideshowId)) {
			builder.queryParam("slideshow_id", slideshowId);
		}
		if (StringUtils.hasLength(slideshowUrl)) {
			builder.queryParam("slideshow_id", slideshowUrl);
		}
		if (StringUtils.hasLength(username)) {
			builder.queryParam("username", username);
		}
		if (StringUtils.hasLength(password)) {
			builder.queryParam("password", password);
		}
		builder.queryParam("exclude_tags", excludeTags ? "1" : "0");
		builder.queryParam("detailed", detailed ? "1" : "0");

		String url = builder.toUriString();
		Slideshow slideshow = this.restOperations.getForObject(url, Slideshow.class);
		return slideshow;
	}

	public List<Slideshow> getSlideshowsByTag(String tag, int limit, int offset, boolean detailed) {
		UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(GET_SLIDESHOW_URL);

		builder.queryParam("tag", tag);
		builder.queryParam("limit", limit);  // optional in api, but not specifying limit returns 0 result.
		if (offset > 0) {
			builder.queryParam("offset", offset);
		}
		if (detailed) {
			// when detailed==true, add "detailed" parameter
			builder.queryParam("detailed", 1);
		}

		String url = builder.toUriString();
//		this.restOperations.getForObject(url, Slideshow.class);  // TODO: imple
		return null;

	}
}
