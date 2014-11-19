package org.springframework.social.slideshare.api.impl.xml;

import com.fasterxml.jackson.dataformat.xml.JacksonXmlModule;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.springframework.social.slideshare.api.impl.xml.TestUtils.readFile;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

/**
 * @author Tadaya Tsuyukubo
 */
public class SlideShareServiceErrorMappingTest extends AbstractSlideshareTemplateTest {

	@Test
	public void mapping() throws Exception {

		XmlMapper xmlMapper = new XmlMapper(new JacksonXmlModule() {
			@Override
			public void setupModule(SetupContext context) {
				super.setupModule(context);
				context.setMixInAnnotations(SlideShareServiceError.class, SlideShareServiceErrorMixin.class);
				context.setMixInAnnotations(SlideShareServiceError.Message.class, SlideShareServiceErrorMixin.MessageMixin.class);
			}
		});

		SlideShareServiceError result = xmlMapper.readValue(readFile("response-error.xml"), SlideShareServiceError.class);

		assertThat(result, is(notNullValue()));
		assertThat(result.getMessage(), is(notNullValue()));
		assertThat(result.getMessage().getId(), is(0));
		assertThat(result.getMessage().getValue(), is("MY ERROR"));

	}
}
