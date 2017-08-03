package de.cect.blockid.identitystorage.general.configuration;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * Spring configuration for a REST Client.
 * 
 * @author ctinnes
 * @since blockid-0.0.1
 *
 */
@Configuration
public class RestClientConfiguration {

	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder.build();
	}

}
