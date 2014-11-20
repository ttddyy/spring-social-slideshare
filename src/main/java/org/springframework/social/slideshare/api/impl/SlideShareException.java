package org.springframework.social.slideshare.api.impl;

import org.springframework.social.ApiException;

/**
 * @author Tadaya Tsuyukubo
 */
public class SlideShareException extends ApiException {

	public SlideShareException(String providerId, String message) {
		super(providerId, message);
	}

	public SlideShareException(String providerId, String message, Throwable cause) {
		super(providerId, message, cause);
	}
}
