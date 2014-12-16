package org.springframework.social.slideshare.api;

import org.springframework.social.ApiBinding;

/**
 * Top level operations for SlideShare.
 * Implemented by {@link org.springframework.social.slideshare.api.impl.SlideShareTemplate}.
 *
 * @author Tadaya Tsuyukubo
 */
public interface SlideShare extends ApiBinding {

	SlideshowOperations slideshowOperations();
}
