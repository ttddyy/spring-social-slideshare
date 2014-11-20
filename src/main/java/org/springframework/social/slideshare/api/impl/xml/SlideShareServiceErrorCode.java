package org.springframework.social.slideshare.api.impl.xml;

/**
 * @author Tadaya Tsuyukubo
 */
public enum SlideShareServiceErrorCode {
	NO_API_KEY_PROVIDED(0),
	FAILED_API_VALIDATION(1),
	FAILED_USER_AUTHENTICATION(2),
	MISSING_TITLE(3),
	MISSING_FILE_FOR_UPLOAD(4),
	BLANK_TITLE(5),
	SLIDESHOW_FILE_IS_NOT_A_SOURCE_OBJECT(6),
	INVALID_EXTENSION(7),
	FILE_SIZE_TOO_BIG(8),
	SLIDESHOW_NOT_FOUND(9),
	USER_NOT_FOUND(10),
	GROUP_NOT_FOUND(11),
	NO_TAG_PROVIDED(12),
	TAG_NOT_FOUND(13),
	REQUIRED_PARAMETER_MISSING(14),
	SEARCH_QUERY_CANNOT_BE_BLANK(15),
	INSUFFICIENT_PERMISSIONS(16),
	INCORRECT_PARAMETERS(17),
	ACCOUNT_ALREADY_LINKED(70),
	NO_LINKED_ACCOUNT_FOUND(71),
	USER_NOT_CREATED(72),
	INVALID_APPLICATION_ID(73),
	LOGIN_ALREADY_EXISTS(74),
	EMAIL_ALREADY_EXISTS(75),
	ACCOUNT_EXCEEDED_DAILY_LIMIT(99),
	YOUR_ACCOUNT_HAS_BEEN_BLOCKED(100),
	UNKNOWN(-1);


	private int code;

	SlideShareServiceErrorCode(int code) {
		this.code = code;
	}

	public int geCode() {
		return code;
	}

	public static SlideShareServiceErrorCode valueOf(int code) {
		for (SlideShareServiceErrorCode slideShareServiceErrorCode : values()) {
			if (slideShareServiceErrorCode.geCode() == code) {
				return slideShareServiceErrorCode;
			}
		}
		return UNKNOWN;
	}
}
