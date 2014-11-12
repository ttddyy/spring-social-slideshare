package org.springframework.social.slideshare.api.domain;

import java.util.List;

/**
 * @author Tadaya Tsuyukubo
 */
public class SearchSlideshowsResponse {

	// since API response is nested, it needs to have nested representation...
	public static class MetaInfo {
		private String query;
		private int resultOffset;  //  the offset of this result (if pages were used)
		private int numResults;  // number of results returned
		private int totalResults;  // total number of results

		public String getQuery() {
			return query;
		}

		public void setQuery(String query) {
			this.query = query;
		}

		public int getResultOffset() {
			return resultOffset;
		}

		public void setResultOffset(int resultOffset) {
			this.resultOffset = resultOffset;
		}

		public int getNumResults() {
			return numResults;
		}

		public void setNumResults(int numResults) {
			this.numResults = numResults;
		}

		public int getTotalResults() {
			return totalResults;
		}

		public void setTotalResults(int totalResults) {
			this.totalResults = totalResults;
		}
	}

	private MetaInfo metaInfo;
	private List<Slideshow> slideshows;

	public MetaInfo getMetaInfo() {
		return metaInfo;
	}

	public void setMetaInfo(MetaInfo metaInfo) {
		this.metaInfo = metaInfo;
	}

	public List<Slideshow> getSlideshows() {
		return slideshows;
	}

	public void setSlideshows(List<Slideshow> slideshows) {
		this.slideshows = slideshows;
	}

	//////

	public String getQuery() {
		return metaInfo.query;
	}

	public int getResultOffset() {
		return metaInfo.resultOffset;
	}

	public int getNumResults() {
		return metaInfo.numResults;
	}

	public int getTotalResults() {
		return metaInfo.totalResults;
	}

}
