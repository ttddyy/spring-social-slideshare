package org.springframework.social.slideshare.api.impl.xml;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

/**
 * @author Tadaya Tsuyukubo
 */
@JsonIgnoreProperties ( ignoreUnknown = true )
@JsonDeserialize ( builder = SlideshowIdHolderMixin.SlideshowIdHolderBuilder.class )
public class SlideshowIdHolderMixin {

	// Due to the wrong element name returned by Delete API, uses jackson builder. (commenting out for now)
//	@JacksonXmlProperty ( localName = "SlideShowID" )
//	String id;

	/**
	 * SlideShare documentation specifies return of edit/upload/delete API contains "SlideShowID" element.
	 * Edit and Upload return correct element name, but Delete returns "SlideshowID" which is not capitalizing "show".
	 * Due to the wrong element name, here uses jackson builder to map both cases to SlideshowIdHolder class.
	 * Alternatively, a custom JsonDeserializer which parse both "SlideShowID" and "SlideshowID" can be used at class
	 * level @JsonDeserialize annotation.
	 */
	public static class SlideshowIdHolderBuilder {
		private String id;

		public SlideshowIdHolderBuilder() {
		}

		// Using proper element name. returned by edit and upload APIs
		@JacksonXmlProperty ( localName = "SlideShowID" )
		public SlideshowIdHolderBuilder withCorrectName(String id) {
			this.id = id;
			return this;
		}

		// Delete API returns wrong element name. ("show" is not capitalized.)
		@JacksonXmlProperty ( localName = "SlideshowID" )
		public SlideshowIdHolderBuilder withWrongName(String id) {
			this.id = id;
			return this;
		}

		public SlideshowIdHolder build() {
			SlideshowIdHolder holder = new SlideshowIdHolder();
			holder.setId(this.id);
			return holder;
		}
	}

}
