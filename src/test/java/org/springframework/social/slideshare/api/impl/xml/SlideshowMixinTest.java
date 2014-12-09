package org.springframework.social.slideshare.api.impl.xml;

import org.junit.Test;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.social.slideshare.api.domain.Slideshow;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;
import static org.springframework.social.slideshare.api.impl.xml.TestUtils.readFile;
import static org.springframework.social.slideshare.api.impl.xml.TestUtils.verifyUtcDate;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

/**
 * @author Tadaya Tsuyukubo
 */
public class SlideshowMixinTest extends AbstractSlideshareTemplateTest {

	@Test
	public void slideshowMapping() throws Exception {

		mockServer
				.expect(requestTo(startsWith("https://www.slideshare.net/api/2/get_slideshow")))
				.andExpect(method(HttpMethod.GET))
				.andRespond(withSuccess(readFile("response-get-slideshow.xml"), MediaType.APPLICATION_XML))
		;

		Slideshow slideshow = slideshowOperations.getSlideshow("id", "url", "user", "pass", true, true, true);

		assertThat(slideshow.getId(), is("13343768"));
		assertThat(slideshow.getTitle(), is("MY_TITLE"));
		assertThat(slideshow.getDescription(), is("MY_DESCRIPTION"));
		assertThat(slideshow.getStatus(), is(Slideshow.Status.CONVERTED));
		assertThat(slideshow.getUsername(), is("MY_USERNAME"));
		assertThat(slideshow.getThumbnailUrl(), is("MY_THUMBNAIL_URL"));
		assertThat(slideshow.getThumbnailSize(), is("MY_THUMBNAIL_SIZE"));
		assertThat(slideshow.getThumbnailSmallURL(), is("MY_THUMBNAIL_SMALL_URL"));
		assertThat(slideshow.getEmbed(), is("MY_EMBED"));

		verifyUtcDate(slideshow.getCreated(), 2012, 6, 15, 21, 8, 19);  // "2012-06-15 21:08:19 UTC"
		verifyUtcDate(slideshow.getUpdated(), 2012, 6, 19, 17, 29, 50);  // "2012-06-19 17:29:50 UTC"

		assertThat(slideshow.getFormat(), is("pdf"));
		assertThat(slideshow.isDownloadable(), is(true));
		assertThat(slideshow.getDownloadUrl(), is("MY_DOWNLOAD_URL"));
		assertThat(slideshow.getSlideshowType(), is(Slideshow.SlideshowType.PRESENTATION));
		assertThat(slideshow.isInContest(), is(false));
		assertThat(slideshow.getTranscript(), is("MY_TRANSCRIPT"));
		assertThat(slideshow.getUserId(), is("25500108"));
		assertThat(slideshow.getExternalAppUserId(), is("123456789"));
		assertThat(slideshow.getPptLocation(), is("MY_PPT_LOCATION"));
		assertThat(slideshow.getStrippedTitle(), is("MY_STRIPPED_TITLE"));

		assertThat(slideshow.getTags(), hasSize(3));
		assertThat(slideshow.getTags().get(0).getCount(), is(1));
		assertThat(slideshow.getTags().get(0).isUsed(), is(true));
		assertThat(slideshow.getTags().get(0).getName(), is("TAG_1"));
		assertThat(slideshow.getTags().get(1).getCount(), is(1));
		assertThat(slideshow.getTags().get(1).isUsed(), is(false));
		assertThat(slideshow.getTags().get(1).getName(), is("TAG_2"));
		assertThat(slideshow.getTags().get(2).getCount(), is(2));
		assertThat(slideshow.getTags().get(2).isUsed(), is(true));
		assertThat(slideshow.getTags().get(2).getName(), is("TAG_3"));

		assertThat(slideshow.isAudio(), is(false));
		assertThat(slideshow.getNumDownloads(), is(372L));
		assertThat(slideshow.getNumViews(), is(69333L));
		assertThat(slideshow.getNumComments(), is(9L));
		assertThat(slideshow.getNumFavorites(), is(59L));
		assertThat(slideshow.getNumSlides(), is(10L));

		assertThat(slideshow.getRelatedSlideshows(), hasSize(3));
		assertThat(slideshow.getRelatedSlideshows().get(0).getRank(), is(1));
		assertThat(slideshow.getRelatedSlideshows().get(0).getId(), is("14475005"));
		assertThat(slideshow.getRelatedSlideshows().get(1).getRank(), is(2));
		assertThat(slideshow.getRelatedSlideshows().get(1).getId(), is("13127718"));
		assertThat(slideshow.getRelatedSlideshows().get(2).getRank(), is(3));
		assertThat(slideshow.getRelatedSlideshows().get(2).getId(), is("33232373"));

		assertThat(slideshow.isPrivate(), is(false));
		assertThat(slideshow.isFlagged(), is(true));
		assertThat(slideshow.isShowOnSlideShare(), is(false));
		assertThat(slideshow.isSecretUrl(), is(false));
		assertThat(slideshow.isAllowEmbed(), is(false));
		assertThat(slideshow.isShareWithContacts(), is(false));
	}

	// TODO: test with extra xml element
	//  TODO: test with comment in xml
	//  TODO: test with thumbnail size:  ex:  <ThumbnailSize>[170,130]</ThumbnailSize>


}
