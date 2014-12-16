package org.springframework.social.slideshare.api;

import org.springframework.core.io.Resource;
import org.springframework.social.slideshare.api.domain.*;

import java.io.File;
import java.util.Collection;

/**
 * Slideshow related operations.
 *
 * @author Tadaya Tsuyukubo
 */
public interface SlideshowOperations {

	/**
	 * Get slideshow by slideshow_id (detailed=true).
	 *
	 * @param slideshowId slideshow id
	 * @return a detailed slideshow
	 */
	Slideshow getSlideshowById(String slideshowId);

	/**
	 * Get slideshow by slideshow_id (detailed=true).
	 *
	 * @param slideshowId slideshow id
	 * @param username    username of the requesting user
	 * @param password    password of the requesting user
	 * @return a detailed slideshow
	 */
	Slideshow getSlideshowById(String slideshowId, String username, String password);

	/**
	 * Get slideshow by slideshow_id
	 *
	 * @param slideshowId slideshow id
	 * @param username    username of the requesting user
	 * @param password    password of the requesting user
	 * @param excludeTags exclude tags if set to true
	 * @param detailed    get detailed info if set to true
	 * @param transcript  get transcript if set to true
	 * @return a slideshow
	 */
	Slideshow getSlideshowById(String slideshowId, String username, String password, boolean excludeTags, boolean detailed, boolean transcript);

	/**
	 * Get slideshow by slideshow url (detailed=true).
	 *
	 * @param slideshowUrl URL of the slideshow to be fetched
	 * @return a detailed slideshow
	 */
	Slideshow getSlideshowByUrl(String slideshowUrl);


	/**
	 * Get slideshow by slideshow url (detailed=true).
	 *
	 * @param slideshowUrl URL of the slideshow to be fetched
	 * @param username     username of the requesting user
	 * @param password     password of the requesting user
	 * @return a detailed slideshow
	 */
	Slideshow getSlideshowByUrl(String slideshowUrl, String username, String password);

	/**
	 * Get slideshow by slideshow url.
	 *
	 * @param slideshowUrl URL of the slideshow to be fetched
	 * @param username     username of the requesting user
	 * @param password     password of the requesting user
	 * @param excludeTags  exclude tags if set to true
	 * @param detailed     get detailed info if set to true
	 * @param transcript   get transcript if set to true
	 * @return a slideshow
	 */
	Slideshow getSlideshowByUrl(String slideshowUrl, String username, String password, boolean excludeTags, boolean detailed, boolean transcript);

	/**
	 * Get slideshow from either slideshow id or url (detailed=true).
	 * Slideshow id has precedence over url.
	 *
	 * @param slideshowId  slideshow id
	 * @param slideshowUrl URL of the slideshow to be fetched
	 * @return a detailed slideshow
	 */
	Slideshow getSlideshow(String slideshowId, String slideshowUrl);

	/**
	 * Get slideshow from either slideshow id or url (detailed=true).
	 * Slideshow id has precedence over url.
	 *
	 * @param slideshowId  slideshow id
	 * @param slideshowUrl URL of the slideshow to be fetched
	 * @param username     username of the requesting user
	 * @param password     password of the requesting user
	 * @return a detailed slideshow
	 */
	Slideshow getSlideshow(String slideshowId, String slideshowUrl, String username, String password);

	/**
	 * Get slideshow from either slideshow id or url (detailed=true).
	 * Slideshow id has precedence over url.
	 *
	 * @param slideshowId  slideshow id
	 * @param slideshowUrl URL of the slideshow to be fetched
	 * @param username     username of the requesting user
	 * @param password     password of the requesting user
	 * @param excludeTags  exclude tags if set to true
	 * @param detailed     get detailed info if set to true
	 * @param transcript   get transcript if set to true
	 * @return a slideshow
	 */
	Slideshow getSlideshow(String slideshowId, String slideshowUrl, String username, String password, boolean excludeTags, boolean detailed, boolean transcript);


	/**
	 * Get slideshows by tag (detailed=true).
	 * use default offset
	 *
	 * @param tag   a tag name
	 * @param limit number of items to return
	 * @return response object that contains returned slideshows
	 */
	GetSlideshowsResponse getSlideshowsByTag(String tag, int limit);

	/**
	 * Get slideshows by tag.
	 *
	 * @param tag      a tag name
	 * @param limit    number of items to return
	 * @param offset   offset
	 * @param detailed get detailed info if set to true
	 * @return response object that contains returned slideshows
	 */
	GetSlideshowsResponse getSlideshowsByTag(String tag, int limit, int offset, boolean detailed);


	/**
	 * Get slideshows by group (detailed=true).
	 * use default offset
	 *
	 * @param groupName a gropu name
	 * @param limit     number of items to return
	 * @return response object that contains returned slideshows
	 */
	GetSlideshowsResponse getSlideshowsByGroup(String groupName, int limit);

	/**
	 * Get slideshows by group.
	 *
	 * @param groupName a group name
	 * @param limit     number of items to return
	 * @param offset    offset
	 * @param detailed  get detailed info if set to true
	 * @return response object that contains returned slideshows
	 */
	GetSlideshowsResponse getSlideshowsByGroup(String groupName, int limit, int offset, boolean detailed);


	/**
	 * Get slideshows by username (detailed=true).
	 * use default offset
	 *
	 * @param usernameFor username of owner of slideshows
	 * @param limit       number of items to return
	 * @return response object that contains returned slideshows
	 */
	GetSlideshowsResponse getSlideshowsByUser(String usernameFor, int limit);

	/**
	 * Get slideshows by username (detailed=true).
	 * use default offset
	 *
	 * @param usernameFor username of owner of slideshows
	 * @param username    username of the requesting user
	 * @param password    password of the requesting user
	 * @param limit       number of items to return
	 * @return response object that contains returned slideshows
	 */
	GetSlideshowsResponse getSlideshowsByUser(String usernameFor, String username, String password, int limit);

	/**
	 * Get slideshows by username.
	 *
	 * @param usernameFor    username of owner of slideshows
	 * @param username       username of the requesting user
	 * @param password       password of the requesting user
	 * @param limit          number of items to return
	 * @param offset         offset
	 * @param detailed       get detailed info if set to true
	 * @param getUnconverted include unconverted slideshows if set to true
	 * @return
	 */
	GetSlideshowsResponse getSlideshowsByUser(String usernameFor, String username, String password,
											  int limit, int offset, boolean detailed, boolean getUnconverted);


	/**
	 * Returns slideshows according to the search criteria
	 * Use default page which is set to 1
	 * Use default itemsPerPage which is set to 12
	 *
	 * @param q search keyword
	 * @return search response object that contains returned slideshows
	 */
	SearchSlideshowsResponse searchSlideshows(String q);

	/**
	 * Returns slideshows according to the search criteria
	 * Use default itemsPerPage which is set to 12
	 *
	 * @param q    search keyword
	 * @param page page number of the results
	 * @return search response object that contains returned slideshows
	 */
	SearchSlideshowsResponse searchSlideshows(String q, int page);

	/**
	 * Returns slideshows according to the search criteria
	 *
	 * @param q            search keyword
	 * @param page         page number of the results
	 * @param itemsPerPage number of results to return per page,
	 * @return search response object that contains returned slideshows
	 */
	SearchSlideshowsResponse searchSlideshows(String q, int page, int itemsPerPage);

	/**
	 * Returns slideshows according to the search criteria
	 *
	 * @param q              search keyword
	 * @param page           page number of the results
	 * @param itemsPerPage   number of results to return per page,
	 * @param lang           language of slideshows (default is English, 'en')
	 * @param sort           sort order (default is relevance)
	 * @param uploadDate     time period you want to restrict your search to (default is 'any')
	 * @param what           what type of search (default is text search)
	 * @param isDownloadable slideshows that are available to download (default is 'all')
	 * @param fileFormat     file format to search for (default is 'all')
	 * @param fileType       file type to search for (default is 'all')
	 * @param isCC           retrieve results under the Creative Commons license if set to true (default is false)
	 * @param isCCAdapt      retrieve results under the Creative Commons that allow adaption, modification if set to
	 *                       true (default is false)
	 * @param isCCCommercial retrieve results under the Commercial Creative Commons license (default is false)
	 * @param detailed       get detailed info if set to true (default is false)
	 * @param transcript     get transcript if set to true (default is false)
	 * @return
	 */
	SearchSlideshowsResponse searchSlideshows(
			String q, int page, int itemsPerPage, SearchOptions.Language lang, SearchOptions.Sort sort,
			SearchOptions.UploadDate uploadDate, SearchOptions.SearchType what, boolean isDownloadable,
			SearchOptions.FileFormat fileFormat, SearchOptions.FileType fileType, boolean isCC, boolean isCCAdapt,
			boolean isCCCommercial, boolean detailed, boolean transcript);


	/**
	 * Edit existing slideshow with {@link org.springframework.social.slideshare.api.domain.PrivacySetting}.
	 *
	 * @param username             username of the requesting user
	 * @param password             password of the requesting user
	 * @param slideshowId          slideshow id
	 * @param slideshowTitle       slidesow title
	 * @param slideshowDescription slideshow description
	 * @param slideshowTags        slideshow tags (comma separated list of tags)
	 * @param privacySetting       privacy setting object (can be null)
	 * @return edited slideshow id
	 */
	String editSlideshow(String username, String password, String slideshowId, String slideshowTitle,
						 String slideshowDescription, Collection<String> slideshowTags, PrivacySetting privacySetting);

	/**
	 * Edit existing slideshow.
	 *
	 * @param username             username of the requesting user
	 * @param password             password of the requesting user
	 * @param slideshowId          slideshow id
	 * @param slideshowTitle       slidesow title
	 * @param slideshowDescription slideshow description
	 * @param slideshowTags        slideshow tags (comma separated list of tags)
	 * @param makeSlideshowPrivate make slideshow private if set to true. If false is set, slideshow becomes public.
	 *                             (can be null)
	 * @param generateSecretUrl    generate a secret URL for the slideshow if set to true.
	 *                             'makeSlideshowPrivate' needs to be true. (can be null)
	 * @param allowEmbeds          allow web site to embed the slideshow if set to true
	 *                             'makeSlideshowPrivate' needs to be true. (can be null)
	 * @param shareWithContacts    allow contacts can be viewed on slideshow if set to true
	 *                             'makeSlideshowPrivate' needs to be true. (can be null)
	 * @return edited slideshow id
	 */
	String editSlideshow(String username, String password, String slideshowId, String slideshowTitle,
						 String slideshowDescription, Collection<String> slideshowTags, Boolean makeSlideshowPrivate,
						 Boolean generateSecretUrl, Boolean allowEmbeds, Boolean shareWithContacts);


	/**
	 * Delete a slideshow.
	 *
	 * @param username    username of the requesting user
	 * @param password    password of the requesting user
	 * @param slideshowId slideshow id to delete
	 * @return deleted slideshow id
	 */
	String deleteSlideshow(String username, String password, String slideshowId);


	/**
	 * Create a new slideshow from url.
	 * This method requires extra permissions on your slideshare account.
	 *
	 * @param username      username of the requesting user
	 * @param password      password of the requesting user
	 * @param uploadUrl     url pointing to the presentation file
	 * @param title         slideshow title
	 * @param description   slideshow description
	 * @param tags          slideshow tags
	 * @param makeSrcPublic allow user to download uploaded file if set to true. (default is true) (can be null)
	 * @return new slideshow id
	 */
	String uploadSlideshowFromUrl(String username, String password, String uploadUrl, String title,
								  String description, Collection<String> tags, Boolean makeSrcPublic);

	/**
	 * Create a new slideshow from url with {@link org.springframework.social.slideshare.api.domain.PrivacySetting}.
	 * This method requires extra permissions on your slideshare account.
	 *
	 * @param username       username of the requesting user
	 * @param password       password of the requesting user
	 * @param uploadUrl      url pointing to the presentation file
	 * @param title          slideshow title
	 * @param description    slideshow description
	 * @param tags           slideshow tags
	 * @param makeSrcPublic  allow user to download uploaded file if set to true. (default is true) (can be null)
	 * @param privacySetting privacy setting object (can be null)
	 * @return new slideshow id
	 */
	String uploadSlideshowFromUrl(String username, String password, String uploadUrl, String title, String description,
								  Collection<String> tags, Boolean makeSrcPublic, PrivacySetting privacySetting);

	/**
	 * Create a new slideshow from url.
	 * This method requires extra permissions on your slideshare account.
	 *
	 * @param username             username of the requesting user
	 * @param password             password of the requesting user
	 * @param uploadUrl            url pointing to the presentation file
	 * @param title                slideshow title
	 * @param description          slideshow description
	 * @param tags                 slideshow tags
	 * @param makeSrcPublic        allow user to download uploaded file if set to true. (default is true) (can be null)
	 * @param makeSlideshowPrivate make slideshow private if set to true. If false is set, slideshow becomes public.
	 *                             (can be null)
	 * @param generateSecretUrl    generate a secret URL for the slideshow if set to true.
	 *                             'makeSlideshowPrivate' needs to be true. (can be null)
	 * @param allowEmbeds          allow web site to embed the slideshow if set to true
	 *                             'makeSlideshowPrivate' needs to be true. (can be null)
	 * @param shareWithContacts    allow contacts can be viewed on slideshow if set to true
	 *                             'makeSlideshowPrivate' needs to be true. (can be null)
	 * @return new slideshow id
	 */
	String uploadSlideshowFromUrl(String username, String password, String uploadUrl, String title, String description,
								  Collection<String> tags, Boolean makeSrcPublic, Boolean makeSlideshowPrivate,
								  Boolean generateSecretUrl, Boolean allowEmbeds, Boolean shareWithContacts);

	/**
	 * Create a new slideshow from local file.
	 * This method requires extra permissions on your slideshare account.
	 *
	 * @param username      username of the requesting user
	 * @param password      password of the requesting user
	 * @param slideshowFile local presentation file
	 * @param title         slideshow title
	 * @param description   slideshow description
	 * @return new slideshow id
	 */
	String uploadSlideshowFromFile(String username, String password, File slideshowFile, String title, String description);

	/**
	 * Create a new slideshow from local file with {@link org.springframework.social.slideshare.api.domain.PrivacySetting}.
	 * This method requires extra permissions on your slideshare account.
	 *
	 * @param username       username of the requesting user
	 * @param password       password of the requesting user
	 * @param slideshowFile  local presentation file
	 * @param title          slideshow title
	 * @param description    slideshow description
	 * @param tags           slideshow tags
	 * @param makeSrcPublic  allow user to download uploaded file if set to true. (default is true) (can be null)
	 * @param privacySetting privacy setting object (can be null)
	 * @return new slideshow id
	 */
	String uploadSlideshowFromFile(String username, String password, File slideshowFile, String title,
								   String description, Collection<String> tags, boolean makeSrcPublic,
								   PrivacySetting privacySetting);

	/**
	 * Create a new slideshow from local file.
	 * This method requires extra permissions on your slideshare account.
	 *
	 * @param username             username of the requesting user
	 * @param password             password of the requesting user
	 * @param slideshowFile        local presentation file
	 * @param title                slideshow title
	 * @param description          slideshow description
	 * @param tags                 slideshow tags
	 * @param makeSrcPublic        allow user to download uploaded file if set to true. (default is true) (can be null)
	 * @param makeSlideshowPrivate make slideshow private if set to true. If false is set, slideshow becomes public.
	 *                             (can be null)
	 * @param generateSecretUrl    generate a secret URL for the slideshow if set to true.
	 *                             'makeSlideshowPrivate' needs to be true. (can be null)
	 * @param allowEmbeds          allow web site to embed the slideshow if set to true
	 *                             'makeSlideshowPrivate' needs to be true. (can be null)
	 * @param shareWithContacts    allow contacts can be viewed on slideshow if set to true
	 *                             'makeSlideshowPrivate' needs to be true. (can be null)
	 * @return new slideshow id
	 */
	String uploadSlideshowFromFile(String username, String password, File slideshowFile, String title,
								   String description, Collection<String> tags, boolean makeSrcPublic,
								   Boolean makeSlideshowPrivate, Boolean generateSecretUrl, Boolean allowEmbeds,
								   Boolean shareWithContacts);

	/**
	 * Create a new slideshow from local file path.
	 *
	 * @param username             username of the requesting user
	 * @param password             password of the requesting user
	 * @param slideshowFilePath    path to the local presentation file
	 * @param title                slideshow title
	 * @param description          slideshow description
	 * @param tags                 slideshow tags
	 * @param makeSrcPublic        allow user to download uploaded file if set to true. (default is true) (can be null)
	 * @param makeSlideshowPrivate make slideshow private if set to true. If false is set, slideshow becomes public.
	 *                             (can be null)
	 * @param generateSecretUrl    generate a secret URL for the slideshow if set to true.
	 *                             'makeSlideshowPrivate' needs to be true. (can be null)
	 * @param allowEmbeds          allow web site to embed the slideshow if set to true
	 *                             'makeSlideshowPrivate' needs to be true. (can be null)
	 * @param shareWithContacts    allow contacts can be viewed on slideshow if set to true
	 *                             'makeSlideshowPrivate' needs to be true. (can be null)
	 * @return new slideshow id
	 */
	String uploadSlideshowFromFile(String username, String password, String slideshowFilePath, String title,
								   String description, Collection<String> tags, boolean makeSrcPublic,
								   Boolean makeSlideshowPrivate, Boolean generateSecretUrl, Boolean allowEmbeds,
								   Boolean shareWithContacts);

	/**
	 * Create a new slideshow from its content with {@link org.springframework.social.slideshare.api.domain.PrivacySetting}.
	 *
	 * @param username         username of the requesting user
	 * @param password         password of the requesting user
	 * @param slideshowContent slideshow content
	 * @param filename         filename (used to detect file type from extension)
	 * @param title            slideshow title
	 * @param description      slideshow description
	 * @param tags             slideshow tags
	 * @param makeSrcPublic    allow user to download uploaded file if set to true. (default is true) (can be null)
	 * @param privacySetting   privacy setting object (can be null)
	 * @return new slideshow id
	 */
	String uploadSlideshowFromContent(String username, String password, String slideshowContent, String filename,
									  String title, String description, Collection<String> tags,
									  boolean makeSrcPublic, PrivacySetting privacySetting);

	/**
	 * Create a new slideshow from its content.
	 *
	 * @param username             username of the requesting user
	 * @param password             password of the requesting user
	 * @param slideshowContent     slideshow content
	 * @param filename             filename (used to detect file type from extension)
	 * @param title                slideshow title
	 * @param description          slideshow description
	 * @param tags                 slideshow tags
	 * @param makeSrcPublic        allow user to download uploaded file if set to true. (default is true) (can be null)
	 * @param makeSlideshowPrivate make slideshow private if set to true. If false is set, slideshow becomes public.
	 *                             (can be null)
	 * @param generateSecretUrl    generate a secret URL for the slideshow if set to true.
	 *                             'makeSlideshowPrivate' needs to be true. (can be null)
	 * @param allowEmbeds          allow web site to embed the slideshow if set to true
	 *                             'makeSlideshowPrivate' needs to be true. (can be null)
	 * @param shareWithContacts    allow contacts can be viewed on slideshow if set to true
	 *                             'makeSlideshowPrivate' needs to be true. (can be null)
	 * @return new slideshow id
	 */
	String uploadSlideshowFromContent(String username, String password, byte[] slideshowContent, String filename, String title,
									  String description, Collection<String> tags, boolean makeSrcPublic,
									  Boolean makeSlideshowPrivate, Boolean generateSecretUrl, Boolean allowEmbeds,
									  Boolean shareWithContacts);

	/**
	 * Create a new slideshow from its content.
	 *
	 * @param username             username of the requesting user
	 * @param password             password of the requesting user
	 * @param slideshowContent     slideshow content
	 * @param filename             filename (used to detect file type from extension)
	 * @param title                slideshow title
	 * @param description          slideshow description
	 * @param tags                 slideshow tags
	 * @param makeSrcPublic        allow user to download uploaded file if set to true. (default is true) (can be null)
	 * @param makeSlideshowPrivate make slideshow private if set to true. If false is set, slideshow becomes public.
	 *                             (can be null)
	 * @param generateSecretUrl    generate a secret URL for the slideshow if set to true.
	 *                             'makeSlideshowPrivate' needs to be true. (can be null)
	 * @param allowEmbeds          allow web site to embed the slideshow if set to true
	 *                             'makeSlideshowPrivate' needs to be true. (can be null)
	 * @param shareWithContacts    allow contacts can be viewed on slideshow if set to true
	 *                             'makeSlideshowPrivate' needs to be true. (can be null)
	 * @return new slideshow id
	 */
	String uploadSlideshowFromContent(String username, String password, String slideshowContent, String filename,
									  String title, String description, Collection<String> tags,
									  boolean makeSrcPublic, Boolean makeSlideshowPrivate,
									  Boolean generateSecretUrl, Boolean allowEmbeds, Boolean shareWithContacts);

	/**
	 * Create a new slideshow from {@link org.springframework.core.io.Resource}.
	 *
	 * @param username             username of the requesting user
	 * @param password             password of the requesting user
	 * @param slideshowResource    slideshow resource
	 * @param title                slideshow title
	 * @param description          slideshow description
	 * @param tags                 slideshow tags
	 * @param makeSrcPublic        allow user to download uploaded file if set to true. (default is true) (can be null)
	 * @param makeSlideshowPrivate make slideshow private if set to true. If false is set, slideshow becomes public.
	 *                             (can be null)
	 * @param generateSecretUrl    generate a secret URL for the slideshow if set to true.
	 *                             'makeSlideshowPrivate' needs to be true. (can be null)
	 * @param allowEmbeds          allow web site to embed the slideshow if set to true
	 *                             'makeSlideshowPrivate' needs to be true. (can be null)
	 * @param shareWithContacts    allow contacts can be viewed on slideshow if set to true
	 *                             'makeSlideshowPrivate' needs to be true. (can be null)
	 * @return
	 */
	String uploadSlideshowResource(String username, String password, Resource slideshowResource, String title,
								   String description, Collection<String> tags, boolean makeSrcPublic,
								   Boolean makeSlideshowPrivate, Boolean generateSecretUrl, Boolean allowEmbeds,
								   Boolean shareWithContacts);


}
