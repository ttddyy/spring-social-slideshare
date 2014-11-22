package org.springframework.social.slideshare.api.impl;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.social.slideshare.api.impl.xml.JacksonUtils;
import org.springframework.social.slideshare.api.impl.xml.SlideShareServiceError;
import org.springframework.social.slideshare.api.impl.xml.SlideShareServiceErrorCode;
import org.springframework.web.client.DefaultResponseErrorHandler;

import java.io.IOException;

/**
 * @author Tadaya Tsuyukubo
 */
public class SlideshareErrorHandler extends DefaultResponseErrorHandler {

	@Override
	public boolean hasError(ClientHttpResponse response) throws IOException {

		if (super.hasError(response)) {
			return true;
		}

		// if response is successfully mapped to SlideShareServiceError, the response is error response
		XmlMapper xmlMapper = JacksonUtils.XML_MAPPER;
		SlideShareServiceError error = xmlMapper.readValue(response.getBody(), SlideShareServiceError.class);
		if (error != null && error.getMessage() != null) {
			return true;
		}
		return false;
	}

	@Override
	public void handleError(ClientHttpResponse response) throws IOException {

		XmlMapper xmlMapper = JacksonUtils.XML_MAPPER;
		SlideShareServiceError error = xmlMapper.readValue(response.getBody(), SlideShareServiceError.class);
		SlideShareServiceErrorCode errorCode = error.getErrorCode();
		String message = "SlideShare API returned error response: " + errorCode;
		throw new SlideShareServiceErrorException(error.getErrorCode(), message);
	}
}
