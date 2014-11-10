package org.springframework.social.slideshare.api.impl.xml;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlText;
import org.springframework.social.slideshare.api.domain.Slideshow;

/**
 * @author Tadaya Tsuyukubo
 */
@JsonIgnoreProperties (ignoreUnknown = true)
public abstract class SlideshowMixIn {

	@JsonIgnoreProperties ( ignoreUnknown = true )
	public static abstract class TagMixin {
		@JacksonXmlProperty ( isAttribute = true, localName = "Count" )
		String count;
		@JacksonXmlProperty ( isAttribute = true, localName = "Owner" )
		@JsonDeserialize ( using = NumberToBooleanDeserializer.class )
		String used;
		@JacksonXmlText
		String name;
	}

	@JsonIgnoreProperties ( ignoreUnknown = true )
	public static abstract class RelatedSlideshowMixin {
		@JacksonXmlProperty ( isAttribute = true, localName = "rank" )
		String rank;
		@JacksonXmlText
		String id;
	}

	@JacksonXmlProperty ( localName = "ID" )
	String id;
	@JacksonXmlProperty ( localName = "Title" )
	String title;
	@JacksonXmlProperty ( localName = "Description" )
	String description;
	@JacksonXmlProperty ( localName = "Status" )
	Slideshow.Status status;
	@JacksonXmlProperty ( localName = "Username" )
	String username;
	@JacksonXmlProperty ( localName = "URL" )
	String url;
	@JacksonXmlProperty ( localName = "ThumbnailURL" )
	String thumbnailUrl;
	@JacksonXmlProperty ( localName = "ThumbnailSize" )
	String thumbnailSize;
	@JacksonXmlProperty ( localName = "ThumbnailSmallURL" )
	String thumbnailSmallURL;
	@JacksonXmlProperty ( localName = "Embed" )
	String embed;
	@JacksonXmlProperty (localName = "Created")
	String created;
	@JacksonXmlProperty (localName = "Updated")
	String updated;
	@JacksonXmlProperty (localName = "Language")
	String language;
	@JacksonXmlProperty (localName = "Format")
	String format;
	@JacksonXmlProperty ( localName = "Download" )
	@JsonDeserialize (using = NumberToBooleanDeserializer.class)
	String isDownloadable;
	@JacksonXmlProperty (localName = "DownloadUrl")
	String downloadUrl;
	@JacksonXmlProperty (localName = "SlideshowType")
	String slideshowType;
	@JacksonXmlProperty ( localName = "InContest" )
	@JsonDeserialize (using = NumberToBooleanDeserializer.class)
	String inContest;
	@JacksonXmlProperty (localName = "UserID")
	String userId;
	@JacksonXmlProperty (localName = "ExternalAppUserID")
	String externalAppUserId;
	@JacksonXmlProperty ( localName = "PPTLocation" )
	String pptLocation;
	@JacksonXmlProperty ( localName = "StrippedTitle" )
	String strippedTitle;
	@JacksonXmlProperty ( localName = "Tags" )
	String tags;
	@JacksonXmlProperty ( localName = "Audio" )
	@JsonDeserialize ( using = NumberToBooleanDeserializer.class )
	String audio;
	@JacksonXmlProperty ( localName = "NumDownloads" )
	String numDownloads;
	@JacksonXmlProperty ( localName = "NumViews" )
	String numViews;
	@JacksonXmlProperty ( localName = "NumComments" )
	String numComments;
	@JacksonXmlProperty ( localName = "NumFavorites" )
	String numFavorites;
	@JacksonXmlProperty ( localName = "NumSlides" )
	String numSlides;
	@JacksonXmlProperty ( localName = "RelatedSlideshows" )
	@JsonDeserialize ( using = RelatedSlideshowListDeserializer.class )
	String relatedSlideshows;
	@JacksonXmlProperty ( localName = "PrivacyLevel" )
	@JsonDeserialize ( using = NumberToBooleanDeserializer.class )
	String isPrivate;
	@JacksonXmlProperty ( localName = "FlagVisible" )
	@JsonDeserialize ( using = NumberToBooleanDeserializer.class )
	String isFlagged;
	@JacksonXmlProperty ( localName = "ShowOnSS" )
	@JsonDeserialize ( using = NumberToBooleanDeserializer.class )
	String showOnSlideShare;
	@JacksonXmlProperty ( localName = "SecretURL" )
	@JsonDeserialize ( using = NumberToBooleanDeserializer.class )
	String isSecretUrl;
	@JacksonXmlProperty ( localName = "AllowEmbed" )
	@JsonDeserialize ( using = NumberToBooleanDeserializer.class )
	String allowEmbed;
	@JacksonXmlProperty ( localName = "ShareWithContacts" )
	@JsonDeserialize ( using = NumberToBooleanDeserializer.class )
	String shareWithContacts;

	private static final class RelatedSlideshowListDeserializer extends TrimmedListDeserializer<Slideshow.RelatedSlideshow> {
		@Override
		public Class<Slideshow.RelatedSlideshow> getElementClass() {
			return Slideshow.RelatedSlideshow.class;
		}
	}

}
