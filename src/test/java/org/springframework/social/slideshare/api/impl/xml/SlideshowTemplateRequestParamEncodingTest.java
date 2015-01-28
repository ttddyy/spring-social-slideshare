package org.springframework.social.slideshare.api.impl.xml;

import org.junit.Test;
import org.springframework.http.MediaType;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.containsString;
import static org.springframework.social.slideshare.api.impl.xml.TestUtils.readFile;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

/**
 * @author Tadaya Tsuyukubo
 */
public class SlideshowTemplateRequestParamEncodingTest extends AbstractSlideshareTemplateTest {

	private static final String TITLE = "TITLE \"TITLE";
	private static final String TITLE_PARAM_EXPECTED = "slideshow_title=TITLE%20%22TITLE";

	private static final String DESCRIPTION = "DESC \"DESC";
	private static final String DESCRIPTION_PARAM_EXPECTED = "slideshow_description=DESC%20%22DESC";

	private static final String TAG = "TAG TAG";
	private static final String TAG_PARAM_EXPECTED = "tag=TAG%20TAG";

	private static final String GROUP = "GROUP GROUP";
	private static final String GROUP_PARAM_EXPECTED = "group_name=GROUP%20GROUP";

	private static final List<String> TAGS = Arrays.asList("TAG1 TAG1", "TAG2 TAG2");
	private static final String TAGS_PARAM_EXPECTED = "slideshow_tags=TAG1%20TAG1,TAG2%20TAG2";

	private static final String SEARCH = "SEARCH \"SEARCH";
	private static final String SEARCH_PARAM_EXPECTED = "q=SEARCH%20%22SEARCH";


	@Test
	public void editSlideshow() throws Exception {
		mockServer
				.expect(requestTo(
						allOf(
								containsString(TITLE_PARAM_EXPECTED),
								containsString(DESCRIPTION_PARAM_EXPECTED),
								containsString(TAGS_PARAM_EXPECTED)
						)))
				.andRespond(withSuccess(readFile("response-empty.xml"), MediaType.APPLICATION_XML))
		;

		slideshowOperations.editSlideshow(null, null, null, TITLE, DESCRIPTION, TAGS, null);
	}

	@Test
	public void getSlideshowsByTag() throws Exception {
		mockServer
				.expect(requestTo(containsString(TAG_PARAM_EXPECTED)))
				.andRespond(withSuccess(readFile("response-empty.xml"), MediaType.APPLICATION_XML))
		;

		slideshowOperations.getSlideshowsByTag(TAG, 0);
	}

	@Test
	public void getSlideshowsByGroup() throws Exception {
		mockServer
				.expect(requestTo(containsString(GROUP_PARAM_EXPECTED)))
				.andRespond(withSuccess(readFile("response-empty.xml"), MediaType.APPLICATION_XML))
		;

		slideshowOperations.getSlideshowsByGroup(GROUP, 0);
	}

	@Test
	public void uploadSlideshowFromUrl() throws Exception {
		mockServer
				.expect(requestTo(
						allOf(
								containsString(TITLE_PARAM_EXPECTED),
								containsString(DESCRIPTION_PARAM_EXPECTED),
								containsString(TAGS_PARAM_EXPECTED)
						)))
				.andRespond(withSuccess(readFile("response-empty.xml"), MediaType.APPLICATION_XML))
		;

		slideshowOperations.uploadSlideshowFromUrl(null, null, null, TITLE, DESCRIPTION, TAGS, null);
	}

	@Test
	public void searchSlideshows() throws Exception {
		mockServer
				.expect(requestTo(containsString(SEARCH_PARAM_EXPECTED)))
				.andRespond(withSuccess(readFile("response-empty.xml"), MediaType.APPLICATION_XML))
		;

		slideshowOperations.searchSlideshows(SEARCH);
	}

}
