package org.springframework.social.slideshare.api.impl.xml;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.util.FileCopyUtils;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Date;

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

}
