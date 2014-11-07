package org.springframework.social.slideshare.api.impl.xml;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.springframework.social.slideshare.api.domain.GetSlideshowResponse;
import org.springframework.social.slideshare.api.domain.Slideshow;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.springframework.social.slideshare.api.impl.xml.TestUtils.*;

/**
 * @author Tadaya Tsuyukubo
 */
public class GetSlideshowResponseMappingTest {

	@Test
	public void getSlideshowByType() throws Exception {
		ObjectMapper xmlMapper = createXmlMapper();

		GetSlideshowResponse response = xmlMapper.readValue(readFile("mapping-slideshow-by-tag.xml"), GetSlideshowResponse.class);

		// TODO: test for RequestType
		assertThat(response.getName(), is("slideshare"));
		assertThat(response.getCount(), is(5678));

		assertThat(response.getSlideshows(), hasSize(2));

		Slideshow first = response.getSlideshows().get(0);
		assertThat(first.getId(), is("41231427"));
		assertThat(first.getTitle(), is("TITLE_1"));
		assertThat(first.getDescription(), is("DESCRIPTION_1"));
		assertThat(first.getStatus(), is(Slideshow.Status.CONVERTED));
		assertThat(first.getUsername(), is("USERNAME_1"));
		assertThat(first.getUrl(), is("URL_1"));
		assertThat(first.getThumbnailUrl(), is("THUMBNAIL_URL_1"));
		assertThat(first.getThumbnailSize(), is("[170,130]"));
		assertThat(first.getThumbnailSmallURL(), is("THUMBNAIL_SMALL_URL_1"));
		assertThat(first.getEmbed(), is("EMBED_1"));
		verifyUtcDate(first.getCreated(), 2014, 11, 6, 23, 8, 32);  // 2014-11-06 23:08:32 UTC
		verifyUtcDate(first.getUpdated(), 2014, 11, 6, 23, 9, 34);  // 2014-11-06 23:09:34 UTC
		assertThat(first.getLanguage(), is("es"));
		assertThat(first.getFormat(), is("pptx"));
		assertThat(first.isDownloadable(), is(true));
		assertThat(first.getDownloadUrl(), is("DOWNLOAD_URL_1"));
		assertThat(first.getSlideshowType(), is(Slideshow.SlideshowType.PRESENTATION));
		assertThat(first.isInContest(), is(false));

		Slideshow second = response.getSlideshows().get(1);
		assertThat(second.getId(), is("41231405"));
		assertThat(second.getTitle(), is("TITLE_2"));
		assertThat(second.getDescription(), is("DESCRIPTION_2"));
		assertThat(second.getStatus(), is(Slideshow.Status.CONVERTED));
		assertThat(second.getUsername(), is("USERNAME_2"));
		assertThat(second.getUrl(), is("URL_2"));
		assertThat(second.getThumbnailUrl(), is("THUMBNAIL_URL_2"));
		assertThat(second.getThumbnailSize(), is("[99,999]"));
		assertThat(second.getThumbnailSmallURL(), is("THUMBNAIL_SMALL_URL_2"));
		assertThat(second.getEmbed(), is("EMBED_2"));
		verifyUtcDate(second.getCreated(), 2014, 11, 7, 23, 8, 0);  // 2014-11-07 23:08:00 UTC
		verifyUtcDate(second.getUpdated(), 2014, 11, 7, 23, 9, 32);  // 2014-11-07 23:09:32 UTC
		assertThat(second.getLanguage(), is("es"));
		assertThat(second.getFormat(), is("pptx"));
		assertThat(second.isDownloadable(), is(true));
		assertThat(second.getDownloadUrl(), is("DOWNLOAD_URL_2"));
		assertThat(second.getSlideshowType(), is(Slideshow.SlideshowType.PRESENTATION));
		assertThat(second.isInContest(), is(false));

	}
}
