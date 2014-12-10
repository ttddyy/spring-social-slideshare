package org.springframework.social.slideshare.api.domain;

/**
 * Represent privacy setting related values.
 *
 * @author Tadaya Tsuyukubo
 */
public class PrivacySetting {

	private Boolean makeSlideshowPrivate;
	private Boolean generateSecretUrl;
	private Boolean allowEmbeds;
	private Boolean shareWithContacts;

	public Boolean getMakeSlideshowPrivate() {
		return makeSlideshowPrivate;
	}

	public void setMakeSlideshowPrivate(Boolean makeSlideshowPrivate) {
		this.makeSlideshowPrivate = makeSlideshowPrivate;
	}

	public Boolean getGenerateSecretUrl() {
		return generateSecretUrl;
	}

	public void setGenerateSecretUrl(Boolean generateSecretUrl) {
		this.generateSecretUrl = generateSecretUrl;
	}

	public Boolean getAllowEmbeds() {
		return allowEmbeds;
	}

	public void setAllowEmbeds(Boolean allowEmbeds) {
		this.allowEmbeds = allowEmbeds;
	}

	public Boolean getShareWithContacts() {
		return shareWithContacts;
	}

	public void setShareWithContacts(Boolean shareWithContacts) {
		this.shareWithContacts = shareWithContacts;
	}
}
