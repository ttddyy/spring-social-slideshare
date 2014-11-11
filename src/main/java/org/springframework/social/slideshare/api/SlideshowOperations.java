package org.springframework.social.slideshare.api;

import org.springframework.social.slideshare.api.domain.GetSlideshowResponse;
import org.springframework.social.slideshare.api.domain.Slideshow;

/**
 * @author Tadaya Tsuyukubo
 */
public interface SlideshowOperations {

	// TODO: write convenient methods which have less parameters for getSlideshow()
	Slideshow getSlideshow(String slideshowId, String slideshowUrl, String username, String password, boolean excludeTags, boolean detailed);

	GetSlideshowResponse getSlideshowsByTag(String tag, int limit, int offset, boolean detailed);

}
