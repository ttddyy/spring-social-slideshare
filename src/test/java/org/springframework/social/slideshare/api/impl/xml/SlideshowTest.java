package org.springframework.social.slideshare.api.impl.xml;

import org.junit.Test;
import org.springframework.social.slideshare.api.domain.Slideshow;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertThat;

/**
 * @author Tadaya Tsuyukubo
 */
public class SlideshowTest {

	@Test
	public void specificThumbnailSize() {
		Slideshow slideshow = new Slideshow();
		slideshow.setThumbnailSize("[100,30]");
		assertThat(slideshow.getThumbnailSizeWidth(), is(100));
		assertThat(slideshow.getThumbnailSizeHeight(), is(30));

		slideshow.setThumbnailSize(null);
		assertThat(slideshow.getThumbnailSizeWidth(), is(nullValue()));
		assertThat(slideshow.getThumbnailSizeHeight(), is(nullValue()));

		slideshow.setThumbnailSize("");
		assertThat(slideshow.getThumbnailSizeWidth(), is(nullValue()));
		assertThat(slideshow.getThumbnailSizeHeight(), is(nullValue()));

		slideshow.setThumbnailSize("MALFORMED");
		assertThat(slideshow.getThumbnailSizeWidth(), is(nullValue()));
		assertThat(slideshow.getThumbnailSizeHeight(), is(nullValue()));

	}
}
