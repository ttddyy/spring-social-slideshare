package org.springframework.social.slideshare.api.impl.xml;

import org.junit.Before;
import org.springframework.http.client.BufferingClientHttpRequestFactory;
import org.springframework.social.slideshare.api.SlideshowOperations;
import org.springframework.social.slideshare.api.impl.SlideShareTemplate;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestTemplate;

/**
 * @author Tadaya Tsuyukubo
 */
public class AbstractSlideshareTemplateTest {

	protected SlideshowOperations slideshowOperations;

	protected MockRestServiceServer mockServer;

	@Before
	public void setUp() {
		SlideShareTemplate slideShareTemplate = new SlideShareTemplate("key", "secret");
		RestTemplate restTemplate = slideShareTemplate.getRestTemplate();
		mockServer = MockRestServiceServer.createServer(restTemplate);

		// creating mockServer replaces RequestFactory in restTemplate to RequestMatcherClientHttpRequestFactory.
		// in order to support multiple reads in test, re-wrap the mock requestFactory and set to restTemplate.
		restTemplate.setRequestFactory(new BufferingClientHttpRequestFactory(restTemplate.getRequestFactory()));
		slideshowOperations = slideShareTemplate.slideshowOperations();
	}

}
