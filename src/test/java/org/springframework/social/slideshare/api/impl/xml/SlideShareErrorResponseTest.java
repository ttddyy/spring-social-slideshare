package org.springframework.social.slideshare.api.impl.xml;

import org.junit.Test;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.social.slideshare.api.impl.SlideShareServiceErrorException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.fail;
import static org.springframework.social.slideshare.api.impl.xml.TestUtils.readFile;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

/**
 * @author Tadaya Tsuyukubo
 */
public class SlideShareErrorResponseTest extends AbstractSlideshareTemplateTest {

	@Test
	public void editSlideshow() throws Exception {
		mockServer
				.expect(requestTo(startsWith("https://www.slideshare.net/api/2/edit_slideshow")))
				.andExpect(method(HttpMethod.GET))
				.andRespond(withSuccess(readFile("response-error.xml"), MediaType.APPLICATION_XML))
		;


		try {
			slideshowOperations.editSlideshow(null, null, null, null, null, null, false, false, false, false);
			fail("should throw exception");
		}
		catch (SlideShareServiceErrorException ex) {
			SlideShareServiceErrorCode errorCode = ex.getErrorCode();
			assertThat(errorCode, is(notNullValue()));
			assertThat(errorCode, is(SlideShareServiceErrorCode.NO_API_KEY_PROVIDED));
		}
	}

}
