package org.springframework.social.slideshare.api.domain;

/**
 * @author Tadaya Tsuyukubo
 */
public abstract class SearchOptions {

	public static enum Language {
		All("**"),
		SPANISH("es"),
		PORTUGUESE("pt"),
		FRENCH("fr"),
		ITALIAN("it"),
		DUTCH("nl"),
		GERMAN("de"),
		CHINESE("zh"),
		JAPANESE("ja"),
		KOREAN("ko"),
		ROMANIAN("ro"),
		Other("!!");

		private String code;

		Language(String code) {
			this.code = code;
		}

		public String toCode() {
			return this.code;
		}
	}

	public static enum Sort {
		RELEVANCE,
		MOSTVIEWED,
		MOSTDOWNLOADED,
		LATEST
	}

	public static enum UploadDate {
		ANY,
		WEEK,
		MONTH,
		YEAR
	}

	public static enum SearchType {
		TEXT,
		TAG
	}

	public static enum FileFormat {
		PDF,
		PPT,
		ODP,
		PPS,
		POT
	}

	public static enum FileType {
		ALL,
		PRESENTATIONS,
		DOCUMENTS,
		WEBINARS,
		VIDEOS,
		INFOGRAPHICS   // TODO: verify this option (documented but not exist in API Exploer)
	}


}
