package org.springframework.social.slideshare.api.impl;

import org.springframework.social.slideshare.api.impl.xml.SlideShareServiceErrorCode;

/**
 * @author Tadaya Tsuyukubo
 */
public class SlideShareServiceErrorException extends SlideShareException {

	private SlideShareServiceErrorCode errorCode;

	public SlideShareServiceErrorException(SlideShareServiceErrorCode errorCode, String message) {
		super("slideshare", message);
		this.errorCode = errorCode;
	}

	public SlideShareServiceErrorException(SlideShareServiceErrorCode errorCode, String message, Throwable cause) {
		super("slideshare", message, cause);
		this.errorCode = errorCode;
	}

	public SlideShareServiceErrorCode getErrorCode() {
		return errorCode;
	}
}
