package org.springframework.social.slideshare.api.impl;

import org.springframework.social.slideshare.api.SlideshowOperations;
import org.springframework.social.slideshare.api.domain.GetSlideshowsResponse;
import org.springframework.social.slideshare.api.domain.SearchOptions;
import org.springframework.social.slideshare.api.domain.SearchSlideshowsResponse;
import org.springframework.social.slideshare.api.domain.Slideshow;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestOperations;
import org.springframework.web.util.UriComponentsBuilder;


/**
 * @author Tadaya Tsuyukubo
 */
public class SlideshowTemplate implements SlideshowOperations {
	public static final String BASE_URL = "https://www.slideshare.net/api/2";
	public static final String GET_SLIDESHOW_URL = BASE_URL + "/get_slideshow";
	public static final String GET_SLIDESHOWS_BY_TAG_URL = BASE_URL + "/get_slideshows_by_tag";
	public static final String GET_SLIDESHOWS_BY_GROUP_URL = BASE_URL + "/get_slideshows_by_group";
	public static final String GET_SLIDESHOWS_BY_USER_URL = BASE_URL + "/get_slideshows_by_user";
	public static final String SEARCH_SLIDESHOWS_URL = BASE_URL + "/search_slideshows";

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

	public GetSlideshowsResponse getSlideshowsByTag(String tag, int limit, int offset, boolean detailed) {
		UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(GET_SLIDESHOWS_BY_TAG_URL);

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
		GetSlideshowsResponse response = this.restOperations.getForObject(url, GetSlideshowsResponse.class);
		response.setRequestType(GetSlideshowsResponse.RequestType.BY_TAG);

		return response;
	}

	public GetSlideshowsResponse getSlideshowsByGroup(String groupName, int limit, int offset, boolean detailed) {
		UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(GET_SLIDESHOWS_BY_GROUP_URL);

		builder.queryParam("group_name", groupName);
		builder.queryParam("limit", limit);  // optional in api, but not specifying limit returns 0 result.
		if (offset > 0) {
			builder.queryParam("offset", offset);
		}
		if (detailed) {
			// when detailed==true, add "detailed" parameter
			builder.queryParam("detailed", 1);
		}

		String url = builder.toUriString();
		GetSlideshowsResponse response = this.restOperations.getForObject(url, GetSlideshowsResponse.class);
		response.setRequestType(GetSlideshowsResponse.RequestType.BY_GROUP);

		return response;
	}

	public GetSlideshowsResponse getSlideshowsByUser(String usernameFor, String username, String password,
													 int limit, int offset, boolean detailed, boolean getUnconverted) {

		UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(GET_SLIDESHOWS_BY_USER_URL);

		builder.queryParam("username_for", usernameFor);
		builder.queryParam("limit", limit);  // optional in api, but not specifying limit returns 0 result.
		if (StringUtils.hasLength(username)) {
			builder.queryParam("username", username);
		}
		if (StringUtils.hasLength(password)) {
			builder.queryParam("password", password);
		}
		if (offset > 0) {
			builder.queryParam("offset", offset);
		}
		if (detailed) {
			// when detailed==true, add "detailed" parameter
			builder.queryParam("detailed", 1);
		}
		if (getUnconverted) {
			builder.queryParam("get_unconverted", 1);
		}

		String url = builder.toUriString();  // TODO: add logging
		GetSlideshowsResponse response = this.restOperations.getForObject(url, GetSlideshowsResponse.class);
		response.setRequestType(GetSlideshowsResponse.RequestType.BY_USER);

		return response;
	}

	@Override
	public SearchSlideshowsResponse searchSlideshows(
			String q, int page, int itemsPerPage, SearchOptions.Language lang, SearchOptions.Sort sort,
			SearchOptions.UploadDate uploadDate, SearchOptions.SearchType searchType, boolean downloadableOnly,
			SearchOptions.FileFormat fileformat, SearchOptions.FileType fileType, boolean isCC, boolean isCCAdapt,
			boolean isCCCommercial, boolean detailed, boolean getTranscript) {

		UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(SEARCH_SLIDESHOWS_URL);

		builder.queryParam("q", q);  // required field
		if (page > 0) {
			builder.queryParam("page", page); // The page number of the results. default is 1
		}
		if (itemsPerPage > 0) {
			builder.queryParam("items_per_page", itemsPerPage);  // Number of results to return per page, default is 12, the maximum is 50
		}
		if (lang != null) {
			builder.queryParam("lang", lang.toCode());
		}
		if (sort != null) {
			builder.queryParam("sort", sort.toString().toLowerCase());
		}
		if (uploadDate != null) {
			builder.queryParam("upload_date", uploadDate.toString().toLowerCase());
		}
		if (searchType != null) {
			builder.queryParam("what", searchType.toString().toLowerCase());
		}
		if (downloadableOnly) {
			builder.queryParam("download", 0);  // Slideshows that are available to download; Set to '0' to do this, otherwise default is all slideshows.
		}
		if (fileformat != null) {
			builder.queryParam("fileformat", fileformat.toString().toLowerCase());
		}
		if (fileType != null) {
			builder.queryParam("file_type", fileType.toString().toLowerCase());
		}
		if (isCC) {
			builder.queryParam("cc", 1);  // Set to '1' to retrieve results under the Creative Commons license. Default is '0'
		}
		if (isCCAdapt) {
			builder.queryParam("cc_adapt", 1);  // Set to '1' for results under Creative Commons that allow adaption, modification. Default is '0'
		}
		if (isCCCommercial) {
			builder.queryParam("cc_commercial", 1);  // Set to '1' to retrieve results with the commercial Creative Commons license. Default is '0'
		}
		if (detailed) {
			builder.queryParam("detailed", 1);  //  to include optional information. 1 to include, 0 (default) for basic information.
		}


		String url = builder.toUriString();  // TODO: add logging
		SearchSlideshowsResponse response = this.restOperations.getForObject(url, SearchSlideshowsResponse.class);
		return response;
	}
}
