package org.springframework.social.slideshare.api.impl;

/**
 * @author Tadaya Tsuyukubo
 */
public class SlideShareException extends RuntimeException {

	public SlideShareException(String message) {
		super(message);
	}

	public SlideShareException(String message, Throwable cause) {
		super(message, cause);
	}
}
