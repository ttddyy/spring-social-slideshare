package org.springframework.social.slideshare.api.impl.xml;

import org.junit.Test;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.social.slideshare.api.domain.SearchSlideshowsResponse;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.springframework.social.slideshare.api.impl.xml.TestUtils.readFile;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

/**
 * @author Tadaya Tsuyukubo
 */
public class SearchSlideshowsResponseTest extends AbstractSlideshareTemplateTest {

	@Test
	public void testMapping() throws Exception {

		mockServer
				.expect(requestTo(startsWith("https://www.slideshare.net/api/2/search_slideshows")))
				.andExpect(method(HttpMethod.GET))
				.andRespond(withSuccess(readFile("response-search-slideshows.xml"), MediaType.APPLICATION_XML))
		;


		SearchSlideshowsResponse response = slideshowOperations.searchSlideshows("query", 0, 0, null, null, null, null, false, null, null, false, false, false, false, false);

		assertThat(response.getMetaInfo(), is(notNullValue()));
		assertThat(response.getMetaInfo().getQuery(), is("MY_QUERY"));
		assertThat(response.getMetaInfo().getResultOffset(), is(9));
		assertThat(response.getMetaInfo().getNumResults(), is(99));
		assertThat(response.getMetaInfo().getTotalResults(), is(999));

		// check convenient methods
		assertThat(response.getQuery(), is("MY_QUERY"));
		assertThat(response.getResultOffset(), is(9));
		assertThat(response.getNumResults(), is(99));
		assertThat(response.getTotalResults(), is(999));

		assertThat(response.getSlideshows(), hasSize(0));
	}

	// TODO: test with slideshows
}
