package org.springframework.social.slideshare.api;

import org.springframework.social.slideshare.api.domain.Slideshow;

/**
 * @author Tadaya Tsuyukubo
 */
public interface SlideshowOperations {
	Slideshow getSlideshow(String slideshowId, String slideshowUrl, String username, String password, boolean excludeTags, boolean detailed);

}
