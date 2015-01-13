package org.springframework.social.slideshare.api.impl.xml;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.springframework.social.slideshare.api.impl.xml.TestUtils.readFile;

/**
 * @author Tadaya Tsuyukubo
 */
public class SlideShareServiceErrorMappingTest extends AbstractSlideshareTemplateTest {

	@Test
	public void mapping() throws Exception {

		SlideShareServiceError result = JacksonUtils.XML_MAPPER.readValue(readFile("response-error.xml"), SlideShareServiceError.class);

		assertThat(result, is(notNullValue()));
		assertThat(result.getMessage(), is(notNullValue()));
		assertThat(result.getMessage().getId(), is(0));
		assertThat(result.getMessage().getValue(), is("MY ERROR"));

		assertThat(result.getErrorCode(), is(SlideShareServiceErrorCode.NO_API_KEY_PROVIDED));

	}
}
