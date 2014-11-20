package org.springframework.social.slideshare.api.impl.xml;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.junit.Test;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

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

		XmlMapper xmlMapper = new Jackson2ObjectMapperBuilder()
				.mixIn(SlideShareServiceError.class, SlideShareServiceErrorMixin.class)
				.mixIn(SlideShareServiceError.Message.class, SlideShareServiceErrorMixin.MessageMixin.class)
				.createXmlMapper(true)
				.build();

		SlideShareServiceError result = xmlMapper.readValue(readFile("response-error.xml"), SlideShareServiceError.class);

		assertThat(result, is(notNullValue()));
		assertThat(result.getMessage(), is(notNullValue()));
		assertThat(result.getMessage().getId(), is(0));
		assertThat(result.getMessage().getValue(), is("MY ERROR"));

		assertThat(result.getErrorCode(), is(SlideShareServiceErrorCode.NO_API_KEY_PROVIDED));

	}
}
