package org.springframework.social.slideshare.api.impl.xml;

import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.JacksonXmlModule;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.junit.Test;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.social.slideshare.api.domain.Slideshow;
import org.springframework.util.FileCopyUtils;

import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.arrayWithSize;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * @author Tadaya Tsuyukubo
 */
public class SlideshowMixinTest {

	// TODO: should not create just for test...
	private XmlMapper createXmlMapper() {
		List<Module> modules = new ArrayList<>();
		modules.add(new JacksonXmlModule() {
			@Override
			public void setupModule(SetupContext context) {
				super.setupModule(context);
				context.setMixInAnnotations(Slideshow.class, SlideshowMixIn.class);
				context.setMixInAnnotations(Slideshow.Tag.class, SlideshowMixIn.TagMixin.class);
				context.setMixInAnnotations(Slideshow.RelatedSlideshow.class, SlideshowMixIn.RelatedSlideshowMixin.class);
			}
		});

		return new Jackson2ObjectMapperBuilder()
				.modules(modules)
				.dateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss zzz"))
				.createXmlMapper(true)
				.build();
	}

	private String readFile(String filename) throws IOException {
		Resource resource = new GenericApplicationContext().getResource("classpath:" + filename);
		return FileCopyUtils.copyToString(new InputStreamReader(resource.getInputStream()));
	}

	@Test
	public void slideshowMapping() throws Exception {

		ObjectMapper xmlMapper = createXmlMapper();

		Slideshow slideshow = xmlMapper.readValue(readFile("mapping-slideshow.xml"), Slideshow.class);

		assertThat(slideshow.getId(), is("13343768"));
		assertThat(slideshow.getTitle(), is("MY_TITLE"));
		assertThat(slideshow.getDescription(), is("MY_DESCRIPTION"));
		assertThat(slideshow.getStatus(), is(Slideshow.Status.CONVERTED));
		assertThat(slideshow.getUsername(), is("MY_USERNAME"));
		assertThat(slideshow.getThumbnailUrl(), is("MY_THUMBNAIL_URL"));
		assertThat(slideshow.getThumbnailSize(), is("MY_THUMBNAIL_SIZE"));
		assertThat(slideshow.getThumbnailSmallURL(), is("MY_THUMBNAIL_SMALL_URL"));
		assertThat(slideshow.getEmbed(), is("MY_EMBED"));

		//  input is "2012-06-15 21:08:19 UTC"
		DateTime created = new DateTime(slideshow.getCreated()).withZone(DateTimeZone.UTC);
		assertThat(created.getYear(), is(2012));
		assertThat(created.getMonthOfYear(), is(6));
		assertThat(created.getDayOfMonth(), is(15));
		assertThat(created.getHourOfDay(), is(21));
		assertThat(created.getMinuteOfHour(), is(8));
		assertThat(created.getSecondOfMinute(), is(19));

		//  input is "2012-06-19 17:29:50 UTC"
		DateTime updated = new DateTime(slideshow.getUpdated()).withZone(DateTimeZone.UTC);
		assertThat(updated.getYear(), is(2012));
		assertThat(updated.getMonthOfYear(), is(6));
		assertThat(updated.getDayOfMonth(), is(19));
		assertThat(updated.getHourOfDay(), is(17));
		assertThat(updated.getMinuteOfHour(), is(29));
		assertThat(updated.getSecondOfMinute(), is(50));

		assertThat(slideshow.getLanguage(), is("en"));
		assertThat(slideshow.getFormat(), is("pdf"));
		assertThat(slideshow.isDownloadable(), is(true));
		assertThat(slideshow.getDownloadUrl(), is("MY_DOWNLOAD_URL"));
		assertThat(slideshow.getType(), is(Slideshow.Type.PRESENTATION));
		assertThat(slideshow.isInContest(), is(false));
		assertThat(slideshow.getUserId(), is("25500108"));
		assertThat(slideshow.getExternalAppUserId(), is("123456789"));
		assertThat(slideshow.getPptLocation(), is("MY_PPT_LOCATION"));
		assertThat(slideshow.getStrippedTitle(), is("MY_STRIPPED_TITLE"));

		assertThat(slideshow.getTags(), arrayWithSize(3));
		assertThat(slideshow.getTags()[0].getCount(), is(1));
		assertThat(slideshow.getTags()[0].isUsed(), is(true));
		assertThat(slideshow.getTags()[0].getName(), is("TAG_1"));
		assertThat(slideshow.getTags()[1].getCount(), is(1));
		assertThat(slideshow.getTags()[1].isUsed(), is(false));
		assertThat(slideshow.getTags()[1].getName(), is("TAG_2"));
		assertThat(slideshow.getTags()[2].getCount(), is(2));
		assertThat(slideshow.getTags()[2].isUsed(), is(true));
		assertThat(slideshow.getTags()[2].getName(), is("TAG_3"));

		assertThat(slideshow.isAudio(), is(false));
		assertThat(slideshow.getNumDownloads(), is(372L));
		assertThat(slideshow.getNumViews(), is(69333L));
		assertThat(slideshow.getNumComments(), is(9L));
		assertThat(slideshow.getNumFavorites(), is(59L));
		assertThat(slideshow.getNumSlides(), is(10L));

		assertThat(slideshow.getRelatedSlideshows(), arrayWithSize(3));
		assertThat(slideshow.getRelatedSlideshows()[0].getRank(), is(1));
		assertThat(slideshow.getRelatedSlideshows()[0].getId(), is("14475005"));
		assertThat(slideshow.getRelatedSlideshows()[1].getRank(), is(2));
		assertThat(slideshow.getRelatedSlideshows()[1].getId(), is("13127718"));
		assertThat(slideshow.getRelatedSlideshows()[2].getRank(), is(3));
		assertThat(slideshow.getRelatedSlideshows()[2].getId(), is("33232373"));

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
