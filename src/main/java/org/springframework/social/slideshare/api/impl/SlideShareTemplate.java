package org.springframework.social.slideshare.api.impl;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.ResourceHttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.support.AllEncompassingFormHttpMessageConverter;
import org.springframework.http.converter.xml.MappingJackson2XmlHttpMessageConverter;
import org.springframework.social.slideshare.api.SlideShare;
import org.springframework.social.slideshare.api.SlideshowOperations;
import org.springframework.social.slideshare.api.impl.xml.JacksonUtils;
import org.springframework.social.support.ClientHttpRequestFactorySelector;
import org.springframework.social.support.HttpRequestDecorator;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This is the central class to interact with SlideShare.
 *
 * @author Tadaya Tsuyukubo
 */
// since SlideShare doesn't use OAuth, not extending any abstract class from spring-social-core
public class SlideShareTemplate implements SlideShare {

	private static final Log logger = LogFactory.getLog(SlideShareTemplate.class);

	private final RestTemplate restTemplate;
	private SlideshowOperations slideshowOperations;


	public SlideShareTemplate(String apiKey, String sharedSecret) {
		// TODO: cleanup initial setup of these objects
		this.restTemplate = createRestTemplateWithCulledMessageConverters();
		registerSlideShareInterceptor(apiKey, sharedSecret);
		configureRestTemplate(this.restTemplate);

		// sub apis
		this.slideshowOperations = new SlideshowTemplate(this.restTemplate);
	}

	private void configureRestTemplate(RestTemplate restTemplate) {
		restTemplate.setErrorHandler(new SlideshareErrorHandler());
	}

	private void registerSlideShareInterceptor(String apiKey, String sharedSecret) {
		List<ClientHttpRequestInterceptor> interceptors = this.restTemplate.getInterceptors();
		interceptors.add(new SlideShareApiValidationParameterRequestInterceptor(apiKey, sharedSecret));
		this.restTemplate.setInterceptors(interceptors);
	}

	private RestTemplate createRestTemplateWithCulledMessageConverters() {

		// need to buffer the response in order to check the response was error or not.
		// http status is always 200 but returned xml is error xml.
		ClientHttpRequestFactory requestFactory = ClientHttpRequestFactorySelector.getRequestFactory();
		ClientHttpRequestFactory bufferedRequestFactory = ClientHttpRequestFactorySelector.bufferRequests(requestFactory);

		List<HttpMessageConverter<?>> messageConverters = getMessageConverters();
		RestTemplate client = new RestTemplate(messageConverters);
		client.setRequestFactory(bufferedRequestFactory);
		return client;
	}

	protected List<HttpMessageConverter<?>> getMessageConverters() {
		List<HttpMessageConverter<?>> messageConverters = new ArrayList<>();
		messageConverters.add(new StringHttpMessageConverter());
		messageConverters.add(new ResourceHttpMessageConverter());
		messageConverters.add(new AllEncompassingFormHttpMessageConverter());  // for multipart upload
		messageConverters.add(getXmlConverter());
		return messageConverters;
	}

	protected HttpMessageConverter<?> getXmlConverter() {
		MappingJackson2XmlHttpMessageConverter converter = new MappingJackson2XmlHttpMessageConverter();
		converter.setObjectMapper(JacksonUtils.XML_MAPPER);
		return converter;
	}

	@Override
	public SlideshowOperations slideshowOperations() {
		return this.slideshowOperations;
	}

	@Override
	public boolean isAuthorized() {
		return false;
	}

	private static final class SlideShareApiValidationParameterRequestInterceptor implements
																				  ClientHttpRequestInterceptor {
		private final String apiKey;
		private final String sharedSecret;

		public SlideShareApiValidationParameterRequestInterceptor(String apiKey, String sharedSecret) {
			this.apiKey = apiKey;
			this.sharedSecret = sharedSecret;
		}

		public ClientHttpResponse intercept(final HttpRequest request, final byte[] body,
											ClientHttpRequestExecution execution) throws
																				  IOException {
			HttpRequest protectedResourceRequest = new HttpRequestDecorator(request) {
				@Override
				public URI getURI() {

					// TODO: refactor
					Date now = new Date();
					String ts = Long.toString(now.getTime() / 1000);
					String hash = DigestUtils.sha1Hex(sharedSecret + ts).toLowerCase();

					UriComponentsBuilder builder = UriComponentsBuilder.fromUri(super.getURI());
					builder.queryParam("api_key", apiKey);
					builder.queryParam("ts", ts);
					builder.queryParam("hash", hash);

					logger.debug("requesting SlideShare API: " + builder.toUriString());

					return builder.build().toUri();
				}
			};

			return execution.execute(protectedResourceRequest, body);
		}

	}

	public RestTemplate getRestTemplate() {
		return restTemplate;
	}
}
