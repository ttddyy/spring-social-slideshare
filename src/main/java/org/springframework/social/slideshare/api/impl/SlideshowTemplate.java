package org.springframework.social.slideshare.api.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.social.slideshare.api.SlideshowOperations;
import org.springframework.social.slideshare.api.domain.GetSlideshowsResponse;
import org.springframework.social.slideshare.api.domain.SearchOptions;
import org.springframework.social.slideshare.api.domain.SearchSlideshowsResponse;
import org.springframework.social.slideshare.api.domain.Slideshow;
import org.springframework.social.slideshare.api.impl.xml.SlideshowIdHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestOperations;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Collection;


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
	public static final String EDIT_SLIDESHOW_URL = BASE_URL + "/edit_slideshow";
	public static final String DELETE_SLIDESHOW_URL = BASE_URL + "/delete_slideshow";
	public static final String UPLOAD_SLIDESHOW_URL = BASE_URL + "/upload_slideshow";

	private static Log logger = LogFactory.getLog(SlideshowTemplate.class);

	private final RestOperations restOperations;

	public SlideshowTemplate(RestOperations restOperations) {
		this.restOperations = restOperations;
	}

	@Override
	public Slideshow getSlideshowById(String slideshowId) {
		return getSlideshow(slideshowId, null, null, null, false, true, true);  // get maximum info
	}

	@Override
	public Slideshow getSlideshowById(String slideshowId, String username, String password) {
		return getSlideshow(slideshowId, null, username, password, false, true, true);  // get maximum info
	}

	@Override
	public Slideshow getSlideshowById(String slideshowId, String username, String password, boolean excludeTags, boolean detailed, boolean transcript) {
		return getSlideshow(slideshowId, null, username, password, excludeTags, detailed, transcript);
	}

	@Override
	public Slideshow getSlideshowByUrl(String slideshowUrl) {
		return getSlideshow(null, slideshowUrl, null, null, false, true, true);  // get maximum info
	}

	@Override
	public Slideshow getSlideshowByUrl(String slideshowUrl, String username, String password) {
		return getSlideshow(null, slideshowUrl, username, password, false, true, true);  // get maximum info
	}

	@Override
	public Slideshow getSlideshowByUrl(String slideshowUrl, String username, String password, boolean excludeTags, boolean detailed, boolean transcript) {
		return getSlideshow(null, slideshowUrl, username, password, excludeTags, detailed, transcript);
	}

	@Override
	public Slideshow getSlideshow(String slideshowId, String slideshowUrl) {
		return getSlideshow(slideshowId, slideshowUrl, null, null, false, true, true);  // get maximum info
	}

	@Override
	public Slideshow getSlideshow(String slideshowId, String slideshowUrl, String username, String password) {
		return getSlideshow(slideshowId, slideshowUrl, username, password, false, true, true);  // get maximum info
	}

	@Override
	public Slideshow getSlideshow(String slideshowId, String slideshowUrl, String username, String password,
								  boolean excludeTags, boolean detailed, boolean transcript) {
		if (StringUtils.isEmpty(slideshowId) && StringUtils.isEmpty(slideshowUrl)) {
			throw new SlideShareException("slideshow", "slideshowId and slideshowUrl are empty");
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
		builder.queryParam("get_transcript", transcript ? "1" : "0");

		String url = builder.toUriString();
		logger.debug("requesting SlideShare API: " + url);

		Slideshow slideshow = this.restOperations.getForObject(url, Slideshow.class);
		return slideshow;
	}

	@Override
	public GetSlideshowsResponse getSlideshowsByTag(String tag, int limit) {
		return getSlideshowsByTag(tag, limit, 0, true);  // get maximum info
	}

	@Override
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
		logger.debug("requesting SlideShare API: " + url);

		GetSlideshowsResponse response = this.restOperations.getForObject(url, GetSlideshowsResponse.class);
		response.setRequestType(GetSlideshowsResponse.RequestType.BY_TAG);

		return response;
	}

	@Override
	public GetSlideshowsResponse getSlideshowsByGroup(String groupName, int limit) {
		return getSlideshowsByGroup(groupName, limit, 0, true);  // get maximum info
	}

	@Override
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
		logger.debug("requesting SlideShare API: " + url);

		GetSlideshowsResponse response = this.restOperations.getForObject(url, GetSlideshowsResponse.class);
		response.setRequestType(GetSlideshowsResponse.RequestType.BY_GROUP);

		return response;
	}

	@Override
	public GetSlideshowsResponse getSlideshowsByUser(String usernameFor, int limit) {
		return getSlideshowsByUser(usernameFor, null, null, limit, 0, true, false);
	}

	@Override
	public GetSlideshowsResponse getSlideshowsByUser(String usernameFor, String username, String password, int limit) {
		return getSlideshowsByUser(usernameFor, username, password, limit, 0, true, false);
	}

	@Override
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

		String url = builder.toUriString();
		logger.debug("requesting SlideShare API: " + url);

		GetSlideshowsResponse response = this.restOperations.getForObject(url, GetSlideshowsResponse.class);
		response.setRequestType(GetSlideshowsResponse.RequestType.BY_USER);

		return response;
	}

	@Override
	public SearchSlideshowsResponse searchSlideshows(String q) {
		return searchSlideshows(q, 0, 0, null, null, null, null, false, null, null, false, false, false, true, true);
	}

	@Override
	public SearchSlideshowsResponse searchSlideshows(String q, int page) {
		return searchSlideshows(q, page, 0, null, null, null, null, false, null, null, false, false, false, true, true);
	}

	@Override
	public SearchSlideshowsResponse searchSlideshows(String q, int page, int itemsPerPage) {
		return searchSlideshows(q, page, itemsPerPage, null, null, null, null, false, null, null, false, false, false, true, true);
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


		String url = builder.toUriString();
		logger.debug("requesting SlideShare API: " + url);

		SearchSlideshowsResponse response = this.restOperations.getForObject(url, SearchSlideshowsResponse.class);
		return response;
	}

	// TODO: makeSlideshowPrivate, generateSecretUrl, allowEmbeds, shareWithContacts => Privacy settings (edit & upload)

	//	required: username, password, slideshowId
//	public String editSlideshow(String username, String password, String slideshowId, String slideshowTitle,
//								String slideshowDescription, Collection<String> slideshowTags, PrivacySettings privacySettings) {
	public String editSlideshow(String username, String password, String slideshowId, String slideshowTitle,
								String slideshowDescription, Collection<String> slideshowTags, Boolean makeSlideshowPrivate,
								Boolean generateSecretUrl, Boolean allowEmbeds, Boolean shareWithContacts) {
		UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(EDIT_SLIDESHOW_URL);

		builder.queryParam("username", username);
		builder.queryParam("password", password);
		builder.queryParam("slideshow_id", slideshowId);

		if (StringUtils.hasLength(slideshowTitle)) {
			builder.queryParam("slideshow_title", slideshowTitle);
		}
		if (StringUtils.hasLength(slideshowDescription)) {
			builder.queryParam("slideshow_description", slideshowDescription);
		}
		if (slideshowTags != null) {
			builder.queryParam("slideshow_tags", StringUtils.collectionToCommaDelimitedString(slideshowTags));
		}

		// privacy setings
		setUpPrivacySettings(builder, makeSlideshowPrivate, generateSecretUrl, allowEmbeds, shareWithContacts);


		String url = builder.toUriString();
		logger.debug("requesting SlideShare API: " + url);

		SlideshowIdHolder response = this.restOperations.getForObject(url, SlideshowIdHolder.class);
		return response.getId();
	}

	private void setUpPrivacySettings(UriComponentsBuilder builder, Boolean makeSlideshowPrivate,
									  Boolean generateSecretUrl, Boolean allowEmbeds, Boolean shareWithContacts) {
		if (makeSlideshowPrivate != null) {

			if (makeSlideshowPrivate) {
				builder.queryParam("make_slideshow_private", "Y");

				if (generateSecretUrl != null) {
					builder.queryParam("generate_secret_url", generateSecretUrl ? "Y" : "N");
				}
				if (allowEmbeds != null) {
					builder.queryParam("allow_embeds", allowEmbeds ? "Y" : "N");
				}
				if (shareWithContacts != null) {
					builder.queryParam("share_with_contacts", shareWithContacts ? "Y" : "N");
				}
			}
			else {
				builder.queryParam("make_slideshow_private", "N");
			}

		}
	}

	@Override
	public String deleteSlideshow(String username, String password, String slideshowId) {
		UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(DELETE_SLIDESHOW_URL);

		builder.queryParam("username", username);
		builder.queryParam("password", password);
		builder.queryParam("slideshow_id", slideshowId);

		String url = builder.toUriString();
		logger.debug("requesting SlideShare API: " + url);

		SlideshowIdHolder response = this.restOperations.getForObject(url, SlideshowIdHolder.class);
		return response.getId();
	}

	public String uploadSlideshow(String username, String password, String uploadUrl, String slideshowTitle,
								  String slideshowDescription, Collection<String> slideshowTags, Boolean makeSrcPublic,
								  Boolean makeSlideshowPrivate, Boolean generateSecretUrl, Boolean allowEmbeds,
								  Boolean shareWithContacts) {
		UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(UPLOAD_SLIDESHOW_URL);

		// required params
		builder.queryParam("username", username);
		builder.queryParam("password", password);
		builder.queryParam("upload_url", uploadUrl);
		builder.queryParam("slideshow_title", slideshowTitle);

		// optional params
		populateSlideshowUploadOptionalParameters(
				builder, slideshowDescription, slideshowTags, makeSrcPublic, makeSlideshowPrivate,
				generateSecretUrl, allowEmbeds, shareWithContacts);

		String url = builder.toUriString();
		logger.debug("requesting SlideShare API: " + url);

		SlideshowIdHolder response = this.restOperations.getForObject(url, SlideshowIdHolder.class);
		return response.getId();
	}

/*  TODO: implement this

	// Need extra permission. If you want to upload a file using SlideShare API, please send an email to
	// api@slideshare.com with your developer account username describing the use case.
	public String uploadSlideshowContent(String username, String password, String slideshowTitle, String slideshowContent,
										 String slideshowDescription, Collection<String> slideshowTags, boolean makeSrcPublic,
										 Boolean makeSlideshowPrivate, Boolean generateSecretUrl, Boolean allowEmbeds,
										 Boolean shareWithContacts) {
		UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(EDIT_SLIDESHOW_URL);

		// required params
		builder.queryParam("username", username);
		builder.queryParam("password", password);
		builder.queryParam("slideshow_srcfile", slideshowContent);

		// optional params
		populateSlideshowUploadOptionalParameters(
				builder, slideshowDescription, slideshowTags, makeSrcPublic, makeSlideshowPrivate,
				generateSecretUrl, allowEmbeds, shareWithContacts);

		String url = builder.toUriString();
		logger.debug("requesting SlideShare API: " + url);

		// needs to be POST
		SlideshowIdHolder response = this.restOperations.postForObject(url, SlideshowIdHolder.class);
		return response.getId();
	}
*/

	private void populateSlideshowUploadOptionalParameters(UriComponentsBuilder builder, String slideshowDescription, Collection<String> slideshowTags, Boolean makeSrcPublic,
														   Boolean makeSlideshowPrivate, Boolean generateSecretUrl, Boolean allowEmbeds,
														   Boolean shareWithContacts) {
		// optional params
		if (StringUtils.hasLength(slideshowDescription)) {
			builder.queryParam("slideshow_description", slideshowDescription);
		}
		if (slideshowTags != null) {
			builder.queryParam("slideshow_tags", StringUtils.collectionToCommaDelimitedString(slideshowTags));
		}
		if (makeSrcPublic != null) {
			builder.queryParam("make_src_public", makeSrcPublic ? "Y" : "N");  // default is Y
		}

		// privacy settings
		setUpPrivacySettings(builder, makeSlideshowPrivate, generateSecretUrl, allowEmbeds, shareWithContacts);
	}

}
