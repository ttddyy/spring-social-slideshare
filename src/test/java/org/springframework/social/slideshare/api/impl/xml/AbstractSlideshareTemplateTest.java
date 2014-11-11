package org.springframework.social.slideshare.api.impl.xml;

import org.junit.Before;
import org.springframework.social.slideshare.api.SlideshowOperations;
import org.springframework.social.slideshare.api.impl.SlideShareTemplate;
import org.springframework.test.web.client.MockRestServiceServer;

/**
 * @author Tadaya Tsuyukubo
 */
public class AbstractSlideshareTemplateTest {

	protected SlideshowOperations slideshowOperations;

	protected MockRestServiceServer mockServer;

	@Before
	public void setUp() {
		SlideShareTemplate slideShareTemplate = new SlideShareTemplate("key", "secret");
		mockServer = MockRestServiceServer.createServer(slideShareTemplate.getRestTemplate());
		slideshowOperations = slideShareTemplate.slideshowOperations();
	}

}
