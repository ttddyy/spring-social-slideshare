package org.springframework.social.slideshare.api.impl.xml;

import org.junit.Test;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.springframework.social.slideshare.api.impl.xml.TestUtils.readFile;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

/**
 * @author Tadaya Tsuyukubo
 */
public class EditSlideshowResponseTest extends AbstractSlideshareTemplateTest {

	@Test
	public void editSlideshow() throws Exception {
		mockServer
				.expect(requestTo(startsWith("https://www.slideshare.net/api/2/edit_slideshow")))
				.andExpect(method(HttpMethod.GET))
				.andRespond(withSuccess(readFile("response-edit-slideshow.xml"), MediaType.APPLICATION_XML))
		;


		String response = slideshowOperations.editSlideshow(null, null, null, null, null, null, false, false, false, false);

		assertThat(response, is("12345"));
	}
}
