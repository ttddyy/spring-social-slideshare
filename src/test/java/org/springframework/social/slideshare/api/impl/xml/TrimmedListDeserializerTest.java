package org.springframework.social.slideshare.api.impl.xml;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

/**
 * @author Tadaya Tsuyukubo
 */
public class TrimmedListDeserializerTest {

	private static String WITH_DATA_XML = "" +
			"<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
			"<foo>\n" +
			"  <id>1</id>" +
			"  <name>FOO-1</name>" +
			"  <bars>" +
			"    <bar><id>1</id><name>BAR-1</name></bar>\n" +
			"    <bar><id>2</id><name>BAR-2</name></bar>\n" +
			"  </bars>" +
			"</foo>";
	private static String WITH_ONE_DATA_XML = "" +
			"<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
			"<foo>\n" +
			"  <id>1</id>" +
			"  <name>FOO-1</name>" +
			"  <bars>" +
			"    <bar><id>1</id><name>BAR-1</name></bar>\n" +
			"  </bars>" +
			"</foo>";

	private static String WITH_ONE_DATA_WITH_WHITESPACE_XML = "" +
			"<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
			"<foo>\n" +
			"  <id>1</id>" +
			"  <name>FOO-1</name>" +
			"  <bars>" +
			"    <bar>  <id>1</id>  \n  <name>BAR-1</name>  </bar>\n" +
			"  </bars>" +
			"</foo>";
	private static String WITH_WHITESPACE_MIXED_XML = "" +
			"<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
			"<foo>\n" +
			"  <id>1</id>" +
			"  <name>FOO-1</name>" +
			"  <bars> \n </bars>" +
			"</foo>";
	private static String WITH_LINESEPARATOR_XML = "" +
			"<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
			"<foo>\n" +
			"  <id>1</id>" +
			"  <name>FOO-1</name>" +
			"  <bars>\n</bars>" +
			"</foo>";
	private static String WITH_SPACES_XML = "" +
			"<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
			"<foo>\n" +
			"  <id>1</id>" +
			"  <name>FOO-1</name>" +
			"  <bars> </bars>" +
			"</foo>";
	private static String WITH_NO_SPACES_XML = "" +
			"<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
			"<foo>\n" +
			"  <id>1</id>" +
			"  <name>FOO-1</name>" +
			"  <bars></bars>" +
			"</foo>";


	public static class Foo {
		private int id;
		private String name;
		private List<Bar> bars = new ArrayList<>();

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public List<Bar> getBars() {
			return bars;
		}

		public void setBars(List<Bar> bars) {
			this.bars = bars;
		}
	}

	public static class Bar {
		private int id;
		private String name;

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}
	}

	public static class FooMixin {
		@JacksonXmlProperty
		String id;
		@JacksonXmlProperty
		String name;
		@JacksonXmlProperty
		@JsonDeserialize ( using = BarDeserializer.class )
		String bars;

		public static class BarDeserializer extends TrimmedListDeserializer<Bar> {
			@Override
			public Class<Bar> getElementClass() {
				return Bar.class;
			}
		}

	}

	public static class BarMixin {
		@JacksonXmlProperty
		String id;
		@JacksonXmlProperty
		String name;
	}


	private static XmlMapper xmlMapper;

	static {
		xmlMapper = JacksonUtils.XML_MAPPER.copy();  // make copy for test

		// add mappings for test
		xmlMapper.addMixInAnnotations(Foo.class, FooMixin.class);
		xmlMapper.addMixInAnnotations(Bar.class, BarMixin.class);
	}

	@Test
	public void withData() throws Exception {
		Foo foo = xmlMapper.readValue(WITH_DATA_XML, Foo.class);
		verifyFoo(foo);

		assertThat(foo, is(notNullValue()));
		assertThat(foo.getBars(), hasSize(2));
		assertThat(foo.getBars().get(0).getId(), is(1));
		assertThat(foo.getBars().get(0).getName(), is("BAR-1"));
		assertThat(foo.getBars().get(1).getId(), is(2));
		assertThat(foo.getBars().get(1).getName(), is("BAR-2"));
	}

	@Test
	public void withOneData() throws Exception {
		Foo foo = xmlMapper.readValue(WITH_ONE_DATA_XML, Foo.class);
		verifyFoo(foo);

		assertThat(foo, is(notNullValue()));
		assertThat(foo.getBars(), hasSize(1));
		assertThat(foo.getBars().get(0).getId(), is(1));
		assertThat(foo.getBars().get(0).getName(), is("BAR-1"));
	}

	@Test
	public void withOneDataWithWhitespace() throws Exception {
		Foo foo = xmlMapper.readValue(WITH_ONE_DATA_WITH_WHITESPACE_XML, Foo.class);
		verifyFoo(foo);

		assertThat(foo, is(notNullValue()));
		assertThat(foo.getBars(), hasSize(1));
		assertThat(foo.getBars().get(0).getId(), is(1));
		assertThat(foo.getBars().get(0).getName(), is("BAR-1"));
	}

	@Test
	public void emptyWithWhitepaceMixedData() throws Exception {
		Foo foo = xmlMapper.readValue(WITH_WHITESPACE_MIXED_XML, Foo.class);
		verifyFoo(foo);
		verifyEmptyBars(foo);
	}

	@Test
	public void emptyWithLineseparatorData() throws Exception {
		Foo foo = xmlMapper.readValue(WITH_LINESEPARATOR_XML, Foo.class);
		verifyFoo(foo);
		verifyEmptyBars(foo);
	}

	@Test
	public void emptyWithSpaceData() throws Exception {
		Foo foo = xmlMapper.readValue(WITH_SPACES_XML, Foo.class);
		verifyFoo(foo);
		verifyEmptyBars(foo);
	}

	@Test
	public void emptyWithNoSpaceData() throws Exception {
		Foo foo = xmlMapper.readValue(WITH_NO_SPACES_XML, Foo.class);
		verifyFoo(foo);
		verifyEmptyBars(foo);
	}

	private void verifyFoo(Foo foo) {
		assertThat(foo.getId(), is(1));
		assertThat(foo.getName(), is("FOO-1"));
	}

	private void verifyEmptyBars(Foo foo) {
		assertThat(foo, is(notNullValue()));
		assertThat(foo.getBars(), hasSize(0));
	}
}
