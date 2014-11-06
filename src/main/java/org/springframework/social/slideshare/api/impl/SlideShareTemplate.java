package org.springframework.social.slideshare.api.impl;

import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.JacksonXmlModule;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.http.converter.xml.MappingJackson2XmlHttpMessageConverter;
import org.springframework.social.slideshare.api.SlideShare;
import org.springframework.social.slideshare.api.SlideshowOperations;
import org.springframework.social.slideshare.api.domain.Slideshow;
import org.springframework.social.slideshare.api.impl.xml.SlideshowMixIn;
import org.springframework.social.support.ClientHttpRequestFactorySelector;
import org.springframework.social.support.HttpRequestDecorator;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.net.URI;
import java.text.SimpleDateFormat;
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
		List<HttpMessageConverter<?>> messageConverters = getMessageConverters();
		RestTemplate client = new RestTemplate(messageConverters);
		client.setRequestFactory(ClientHttpRequestFactorySelector.getRequestFactory());
		return client;
	}

	protected List<HttpMessageConverter<?>> getMessageConverters() {
		List<HttpMessageConverter<?>> messageConverters = new ArrayList<HttpMessageConverter<?>>();
		messageConverters.add(new StringHttpMessageConverter());
		messageConverters.add(getXmlConverter());
		return messageConverters;
	}

	protected HttpMessageConverter<?> getXmlConverter() {

		List<Module> modules = new ArrayList<>();
		modules.add(new JacksonXmlModule() {
			@Override
			public void setupModule(SetupContext context) {
				super.setupModule(context);
				context.setMixInAnnotations(Slideshow.class, SlideshowMixIn.class);
				context.setMixInAnnotations(Slideshow.Tag.class, SlideshowMixIn.TagMixin.class);
				context.setMixInAnnotations(Slideshow.RelatedSlideshow.class, SlideshowMixIn.RelatedSlideshowMixin.class);
			}
		});

		ObjectMapper objectMapper =
				new Jackson2ObjectMapperBuilder()
						.modules(modules)
						.dateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss zzz"))  // TODO: cleanup
						.createXmlMapper(true)
						.build();

		MappingJackson2XmlHttpMessageConverter converter = new MappingJackson2XmlHttpMessageConverter();
		converter.setObjectMapper(objectMapper);
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
