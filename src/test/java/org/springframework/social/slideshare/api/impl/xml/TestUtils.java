package org.springframework.social.slideshare.api.impl.xml;

import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.dataformat.xml.JacksonXmlModule;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.social.slideshare.api.domain.GetSlideshowResponse;
import org.springframework.social.slideshare.api.domain.Slideshow;
import org.springframework.util.FileCopyUtils;

import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * @author Tadaya Tsuyukubo
 */
public class TestUtils {

	// TODO: make a hamcrest matcher
	public static void verifyUtcDate(Date date, int year, int month, int day, int hour, int minute, int second) {
		DateTime datetime = new DateTime(date).withZone(DateTimeZone.UTC);
		assertThat("year", datetime.getYear(), is(year));
		assertThat("month", datetime.getMonthOfYear(), is(month));
		assertThat("day", datetime.getDayOfMonth(), is(day));
		assertThat("hour", datetime.getHourOfDay(), is(hour));
		assertThat("minute", datetime.getMinuteOfHour(), is(minute));
		assertThat("second", datetime.getSecondOfMinute(), is(second));
	}

	public static String readFile(String filename) throws IOException {
		Resource resource = new GenericApplicationContext().getResource("classpath:" + filename);
		return FileCopyUtils.copyToString(new InputStreamReader(resource.getInputStream()));
	}

	// TODO: move to main logic
	public static XmlMapper createXmlMapper() {
		List<Module> modules = new ArrayList<>();
		modules.add(new JacksonXmlModule() {
			@Override
			public void setupModule(SetupContext context) {
				super.setupModule(context);
				context.setMixInAnnotations(Slideshow.class, SlideshowMixIn.class);
				context.setMixInAnnotations(Slideshow.Tag.class, SlideshowMixIn.TagMixin.class);
				context.setMixInAnnotations(Slideshow.RelatedSlideshow.class, SlideshowMixIn.RelatedSlideshowMixin.class);

				context.setMixInAnnotations(GetSlideshowResponse.class, GetSlideshowResponseMixin.class);
			}
		});

		return new Jackson2ObjectMapperBuilder()
				.modules(modules)
				.dateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss zzz"))
				.createXmlMapper(true)
				.build();
	}
}
