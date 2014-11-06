package org.springframework.social.slideshare.api.domain;

import java.util.Date;

/**
 * @author Tadaya Tsuyukubo
 */

/*
  <ID>{ slideshow id }</ID>
  <Title>{ slideshow title }</Title>
  <Description>{ slideshow description }</Description>
  <Status>{ 0 if queued for conversion, 1 if converting, 2 if converted,
            3 if conversion failed }
  </Status>
  <Username>{ username }</Username>
  <URL>{ web permalink }</URL>
  <ThumbnailURL>{ thumbnail URL }</ThumbnailURL>
  <ThumbnailSmallURL>{ URL of smaller thumbnail }</ThumbnailSmallURL>
  <Embed>{ embed code }</Embed>
  <Created>{ date slideshow created }</Created>
  <Updated>{ date slideshow was last update }</Updated>
  <Language>{ language, as specified by two-letter code }</Language>
  <Format>ppt (or pdf, pps, odp, doc, pot, txt, rdf) </Format>
  <Download>{ 1 if available to download, else 0 }</Download>
  <DownloadUrl>{ returns if available to download }</DownloadUrl>
  <SlideshowType>{ 0 if presentation, 1 if document, 2 if a portfolio,
    3 if video }</SlideshowType>
  <InContest>{ 1 if part of a contest, 0 if not }</Download>
  <UserID>{ userID }</UserID>
  <ExternalAppUserID>{ ExternalAppUserID if uploaded using an
         external app }</ExternalAppUserID>
  <ExternalAppID>{ ExternalAppID for the external app }</ExternalAppID>
  <PPTLocation>{ PPTLocation }</ExternalAppUserID>
  <StrippedTitle>{ Stripped Title }</StrippedTitle>
  <Tags>
  <Tag Count="{ number of times tag has been used }" Owner="{ 1 if owner
                has used the tag, else 0 }">{ tag name }
  </Tag>
  </Tags>
  <Audio>{ 0, or 1 if the slideshow contains audio }</Audio>
  <NumDownloads>{ number of downloads }</NumDownloads>
  <NumViews>{ number of views }</NumViews>
  <NumComments>{ number of comments }</NumComments>
  <NumFavorites>{ number of favorites }</NumFavorites>
  <NumSlides>{ number of slides }</NumSlides>
  <RelatedSlideshows>
    <RelatedSlideshowID rank="{ rank, where 1 is highest}">
    { slideshow id } </RelatedSlideshowID>
  </RelatedSlideshows>
  <PrivacyLevel>{ 0, or 1 if private }</PrivacyLevel>
  <FlagVisible>{ 1, or 0 if slideshow has been flagged }</FlagVisible>
  <ShowOnSS>{ 0, or 1 if not to be shown on Slideshare }</ShowOnSS>
  <SecretURL>{ 0, or 1 if secret URL is enabled }</SecretURL>
  <AllowEmbed>{ 0, or 1 if embeds are allowed }</AllowEmbed>
  <ShareWithContacts>{ 0, or 1 if set to private, but contacts can view
                       slideshow }
  </ShareWithContacts>
</Slideshow>

 */
public class Slideshow {
	private String id;
	private String title;
	private String description;
	private Status status;
	private String username;
	private String url;
	private String thumbnailUrl;
	private String thumbnailSize;
	private String thumbnailSmallURL;
	private String embed;
	private Date created;
	private Date updated;
	private String language;
	private String format;
	private boolean isDownloadable;
	private String downloadUrl;
	private Type type;
	private boolean inContest;
	private String userId;
	private String externalAppUserId;
	private String pptLocation;
	private String strippedTitle;
	private Tag[] tags;
	private boolean audio;
	private long numDownloads;
	private long numViews;
	private long numComments;
	private long numFavorites;
	private long numSlides;
	private RelatedSlideshow[] relatedSlideshows;
	private boolean isPrivate;
	private boolean isFlagged;
	private boolean showOnSlideShare;
	private boolean isSecretUrl;
	private boolean allowEmbed;
	private boolean shareWithContacts;

	public static enum Status {
		// { 0 if queued for conversion, 1 if converting, 2 if converted, 3 if conversion failed }
		QUEUED,
		CONVERTING,
		CONVERTED,
		FAILED
	}

	public static enum Type {
		// 0 if presentation, 1 if document, 2 if a portfolio, 3 if video
		PRESENTATION,
		DOCUMENT,
		PORTFOLIO,
		VIDEO
	}

	public static class Tag {
//  <Tag Count="{ number of times tag has been used }" Owner="{ 1 if owner has used the tag, else 0 }">{ tag name }

		private int count;
		private boolean used;
		private String name;

		public int getCount() {
			return count;
		}

		public void setCount(int count) {
			this.count = count;
		}

		public boolean isUsed() {
			return used;
		}

		public void setUsed(boolean used) {
			this.used = used;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}
	}

	public static class RelatedSlideshow {
		private int rank;
		private String id;

		public int getRank() {
			return rank;
		}

		public void setRank(int rank) {
			this.rank = rank;
		}

		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}
	}


	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getThumbnailUrl() {
		return thumbnailUrl;
	}

	public void setThumbnailUrl(String thumbnailUrl) {
		this.thumbnailUrl = thumbnailUrl;
	}

	public String getThumbnailSize() {
		return thumbnailSize;
	}

	public void setThumbnailSize(String thumbnailSize) {
		this.thumbnailSize = thumbnailSize;
	}

	public String getThumbnailSmallURL() {
		return thumbnailSmallURL;
	}

	public void setThumbnailSmallURL(String thumbnailSmallURL) {
		this.thumbnailSmallURL = thumbnailSmallURL;
	}

	public String getEmbed() {
		return embed;
	}

	public void setEmbed(String embed) {
		this.embed = embed;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public Date getUpdated() {
		return updated;
	}

	public void setUpdated(Date updated) {
		this.updated = updated;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getFormat() {
		return format;
	}

	public void setFormat(String format) {
		this.format = format;
	}

	public boolean isDownloadable() {
		return isDownloadable;
	}

	public void setDownloadable(boolean isDownloadable) {
		this.isDownloadable = isDownloadable;
	}

	public String getDownloadUrl() {
		return downloadUrl;
	}

	public void setDownloadUrl(String downloadUrl) {
		this.downloadUrl = downloadUrl;
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	public boolean isInContest() {
		return inContest;
	}

	public void setInContest(boolean inContest) {
		this.inContest = inContest;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getExternalAppUserId() {
		return externalAppUserId;
	}

	public void setExternalAppUserId(String externalAppUserId) {
		this.externalAppUserId = externalAppUserId;
	}

	public String getPptLocation() {
		return pptLocation;
	}

	public void setPptLocation(String pptLocation) {
		this.pptLocation = pptLocation;
	}

	public String getStrippedTitle() {
		return strippedTitle;
	}

	public void setStrippedTitle(String strippedTitle) {
		this.strippedTitle = strippedTitle;
	}

	public Tag[] getTags() {
		return tags;
	}

	public void setTags(Tag[] tags) {
		this.tags = tags;
	}

	public boolean isAudio() {
		return audio;
	}

	public void setAudio(boolean audio) {
		this.audio = audio;
	}

	public long getNumDownloads() {
		return numDownloads;
	}

	public void setNumDownloads(long numDownloads) {
		this.numDownloads = numDownloads;
	}

	public long getNumViews() {
		return numViews;
	}

	public void setNumViews(long numViews) {
		this.numViews = numViews;
	}

	public long getNumComments() {
		return numComments;
	}

	public void setNumComments(long numComments) {
		this.numComments = numComments;
	}

	public long getNumFavorites() {
		return numFavorites;
	}

	public void setNumFavorites(long numFavorites) {
		this.numFavorites = numFavorites;
	}

	public long getNumSlides() {
		return numSlides;
	}

	public void setNumSlides(long numSlides) {
		this.numSlides = numSlides;
	}

	public RelatedSlideshow[] getRelatedSlideshows() {
		return relatedSlideshows;
	}

	public void setRelatedSlideshows(RelatedSlideshow[] relatedSlideshows) {
		this.relatedSlideshows = relatedSlideshows;
	}

	public boolean isPrivate() {
		return isPrivate;
	}

	public void setPrivate(boolean isPrivate) {
		this.isPrivate = isPrivate;
	}

	public boolean isFlagged() {
		return isFlagged;
	}

	public void setFlagged(boolean isFlagged) {
		this.isFlagged = isFlagged;
	}

	public boolean isShowOnSlideShare() {
		return showOnSlideShare;
	}

	public void setShowOnSlideShare(boolean showOnSlideShare) {
		this.showOnSlideShare = showOnSlideShare;
	}

	public boolean isSecretUrl() {
		return isSecretUrl;
	}

	public void setSecretUrl(boolean isSecretUrl) {
		this.isSecretUrl = isSecretUrl;
	}

	public boolean isAllowEmbed() {
		return allowEmbed;
	}

	public void setAllowEmbed(boolean allowEmbed) {
		this.allowEmbed = allowEmbed;
	}

	public boolean isShareWithContacts() {
		return shareWithContacts;
	}

	public void setShareWithContacts(boolean shareWithContacts) {
		this.shareWithContacts = shareWithContacts;
	}
}
