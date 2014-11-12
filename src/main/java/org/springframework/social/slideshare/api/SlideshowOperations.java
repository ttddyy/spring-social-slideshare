package org.springframework.social.slideshare.api;

import org.springframework.social.slideshare.api.domain.GetSlideshowsResponse;
import org.springframework.social.slideshare.api.domain.SearchOptions;
import org.springframework.social.slideshare.api.domain.SearchSlideshowsResponse;
import org.springframework.social.slideshare.api.domain.Slideshow;

/**
 * @author Tadaya Tsuyukubo
 */
public interface SlideshowOperations {

	// TODO: write convenient methods which have less parameters for getSlideshow()
	Slideshow getSlideshow(String slideshowId, String slideshowUrl, String username, String password, boolean excludeTags, boolean detailed);

	GetSlideshowsResponse getSlideshowsByTag(String tag, int limit, int offset, boolean detailed);

	SearchSlideshowsResponse searchSlideshows(
			String q, int page, int itemsPerPage, SearchOptions.Language lang, SearchOptions.Sort sort,
			SearchOptions.UploadDate uploadDate, SearchOptions.SearchType what, boolean isDownloadable,
			SearchOptions.FileFormat fileFormat, SearchOptions.FileType fileType, boolean isCC, boolean isCCAdapt,
			boolean isCCCommercial, boolean detailed, boolean getTranscript);
}
