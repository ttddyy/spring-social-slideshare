package org.springframework.social.slideshare.api.impl;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.xml.MappingJackson2XmlHttpMessageConverter;
import org.springframework.social.slideshare.api.SlideShare;
import org.springframework.social.slideshare.api.SlideshowOperations;
import org.springframework.social.support.ClientHttpRequestFactorySelector;
import org.springframework.social.support.HttpRequestDecorator;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Tadaya Tsuyukubo
 */

// since SlideShare doesn't use OAuth, not extending any abstract class from spring-social-core
// TODO: consider expandability, getRestTemplate, protected methods
public class SlideShareTemplate implements SlideShare {

	private final RestTemplate restTemplate;
	private SlideshowOperations slideshowOperations;


	public SlideShareTemplate(String apiKey, String sharedSecret) {
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
		List<HttpMessageConverter<?>> messageConverters = getMessageConverters();
		RestTemplate client = new RestTemplate(messageConverters);
		client.setRequestFactory(ClientHttpRequestFactorySelector.getRequestFactory());
		return client;
	}

	protected List<HttpMessageConverter<?>> getMessageConverters() {
		List<HttpMessageConverter<?>> messageConverters = new ArrayList<HttpMessageConverter<?>>();
		messageConverters.add(new StringHttpMessageConverter());
		messageConverters.add(new MappingJackson2XmlHttpMessageConverter());
		return messageConverters;
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
					String hash = DigestUtils.shaHex(sharedSecret + ts).toLowerCase();

					UriComponentsBuilder builder = UriComponentsBuilder.fromUri(super.getURI());
					builder.queryParam("api_key", apiKey);
					builder.queryParam("ts", ts);
					builder.queryParam("hash", hash);

					// TODO: debug the url
					String url = builder.build().toString();

					return builder.build().toUri();
				}
			};

			return execution.execute(protectedResourceRequest, body);
		}

	}

}
