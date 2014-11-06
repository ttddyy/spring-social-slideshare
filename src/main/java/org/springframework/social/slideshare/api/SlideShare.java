package org.springframework.social.slideshare.api;

import org.springframework.social.ApiBinding;

/**
 * @author Tadaya Tsuyukubo
 */
public interface SlideShare extends ApiBinding {

	SlideshowOperations slideshowOperations();
}
