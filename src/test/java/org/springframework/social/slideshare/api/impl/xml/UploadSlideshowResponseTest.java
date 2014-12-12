package org.springframework.social.slideshare.api.impl.xml;

import org.junit.Test;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.startsWith;
import static org.springframework.social.slideshare.api.impl.xml.TestUtils.readFile;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

/**
 * @author Tadaya Tsuyukubo
 */
public class UploadSlideshowResponseTest extends AbstractSlideshareTemplateTest {

	@Test
	public void uploadSlideshow() throws Exception {
		mockServer
				.expect(requestTo(startsWith("https://www.slideshare.net/api/2/upload_slideshow")))
				.andExpect(method(HttpMethod.GET))
				.andRespond(withSuccess(readFile("response-upload-slideshow.xml"), MediaType.APPLICATION_XML))
		;

		String response = slideshowOperations.uploadSlideshowFromUrl(null, null, null, null, null, null, false, false, false, false, false);

		assertThat(response, is("112233"));
	}

}
