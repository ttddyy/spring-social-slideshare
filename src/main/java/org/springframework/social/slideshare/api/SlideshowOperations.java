package org.springframework.social.slideshare.api;

import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.social.slideshare.api.domain.*;

import java.io.File;
import java.util.Collection;

/**
 * @author Tadaya Tsuyukubo
 */
public interface SlideshowOperations {

	// TODO: javadoc
	Slideshow getSlideshowById(String slideshowId);

	Slideshow getSlideshowById(String slideshowId, String username, String password);

	Slideshow getSlideshowById(String slideshowId, String username, String password, boolean excludeTags, boolean detailed, boolean transcript);

	Slideshow getSlideshowByUrl(String slideshowUrl);

	Slideshow getSlideshowByUrl(String slideshowUrl, String username, String password);

	Slideshow getSlideshowByUrl(String slideshowUrl, String username, String password, boolean excludeTags, boolean detailed, boolean transcript);

	Slideshow getSlideshow(String slideshowId, String slideshowUrl);

	Slideshow getSlideshow(String slideshowId, String slideshowUrl, String username, String password);

	Slideshow getSlideshow(String slideshowId, String slideshowUrl, String username, String password, boolean excludeTags, boolean detailed, boolean transcript);


	GetSlideshowsResponse getSlideshowsByTag(String tag, int limit);

	GetSlideshowsResponse getSlideshowsByTag(String tag, int limit, int offset, boolean detailed);


	GetSlideshowsResponse getSlideshowsByGroup(String groupName, int limit);

	GetSlideshowsResponse getSlideshowsByGroup(String groupName, int limit, int offset, boolean detailed);


	GetSlideshowsResponse getSlideshowsByUser(String usernameFor, int limit);

	GetSlideshowsResponse getSlideshowsByUser(String usernameFor, String username, String password, int limit);

	GetSlideshowsResponse getSlideshowsByUser(String usernameFor, String username, String password,
											  int limit, int offset, boolean detailed, boolean getUnconverted);


	SearchSlideshowsResponse searchSlideshows(String q);

	SearchSlideshowsResponse searchSlideshows(String q, int page);

	SearchSlideshowsResponse searchSlideshows(String q, int page, int itemsPerPage);

	SearchSlideshowsResponse searchSlideshows(
			String q, int page, int itemsPerPage, SearchOptions.Language lang, SearchOptions.Sort sort,
			SearchOptions.UploadDate uploadDate, SearchOptions.SearchType what, boolean isDownloadable,
			SearchOptions.FileFormat fileFormat, SearchOptions.FileType fileType, boolean isCC, boolean isCCAdapt,
			boolean isCCCommercial, boolean detailed, boolean getTranscript);


	String editSlideshow(String username, String password, String slideshowId, String slideshowTitle,
						 String slideshowDescription, Collection<String> slideshowTags, PrivacySetting privacySetting);

	String editSlideshow(String username, String password, String slideshowId, String slideshowTitle,
						 String slideshowDescription, Collection<String> slideshowTags, Boolean makeSlideshowPrivate,
						 Boolean generateSecretUrl, Boolean allowEmbeds, Boolean shareWithContacts);


	String deleteSlideshow(String username, String password, String slideshowId);


	String uploadSlideshowFromUrl(String username, String password, String uploadUrl, String title,
								  String description, Collection<String> tags, Boolean makeSrcPublic);

	String uploadSlideshowFromUrl(String username, String password, String uploadUrl, String title, String description,
								  Collection<String> tags, Boolean makeSrcPublic, PrivacySetting privacySetting);

	String uploadSlideshowFromUrl(String username, String password, String uploadUrl, String title, String description,
								  Collection<String> tags, Boolean makeSrcPublic, Boolean makeSlideshowPrivate,
								  Boolean generateSecretUrl, Boolean allowEmbeds, Boolean shareWithContacts);

	String uploadSlideshowFromFile(String username, String password, File slideshowFile, String title, String description);

	String uploadSlideshowFromFile(String username, String password, File slideshowFile, String title,
								   String description, Collection<String> tags, boolean makeSrcPublic,
								   PrivacySetting privacySetting);

	String uploadSlideshowFromFile(String username, String password, File slideshowFile, String title,
								   String description, Collection<String> tags, boolean makeSrcPublic,
								   Boolean makeSlideshowPrivate, Boolean generateSecretUrl, Boolean allowEmbeds,
								   Boolean shareWithContacts);

	String uploadSlideshowFromFile(String username, String password, String slideshowFilePath, String title,
								   String description, Collection<String> tags, boolean makeSrcPublic,
								   Boolean makeSlideshowPrivate, Boolean generateSecretUrl, Boolean allowEmbeds,
								   Boolean shareWithContacts);

	String uploadSlideshowFromContent(String username, String password, String slideshowContent, String filename,
									  String title, String description, Collection<String> tags,
									  boolean makeSrcPublic, PrivacySetting privacySetting);

	String uploadSlideshowFromContent(String username, String password, byte[] slideshowContent, String filename, String title,
									  String description, Collection<String> tags, boolean makeSrcPublic,
									  Boolean makeSlideshowPrivate, Boolean generateSecretUrl, Boolean allowEmbeds,
									  Boolean shareWithContacts);

	String uploadSlideshowFromContent(String username, String password, String slideshowContent, String filename,
									  String title, String description, Collection<String> tags,
									  boolean makeSrcPublic, Boolean makeSlideshowPrivate,
									  Boolean generateSecretUrl, Boolean allowEmbeds, Boolean shareWithContacts);

	String uploadSlideshowResource(String username, String password, Resource slideshowResource, String title,
								   String description, Collection<String> tags, boolean makeSrcPublic,
								   Boolean makeSlideshowPrivate, Boolean generateSecretUrl, Boolean allowEmbeds,
								   Boolean shareWithContacts);


}
