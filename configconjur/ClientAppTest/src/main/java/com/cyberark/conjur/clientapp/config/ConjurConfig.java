package com.cyberark.conjur.clientapp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;

import com.cyberark.conjur.clientapp.core.ConjurPropertySource;

@Configuration
public class ConjurConfig {

	@Bean
	@Primary
	public ConjurPropertySource getPropertySource() {
		return new ConjurPropertySource();
	}

	@Bean
	public PropertySourcesPlaceholderConfigurer getPropertyplaceHolderconfigureer() {

		PropertySourcesPlaceholderConfigurer cfg = new PropertySourcesPlaceholderConfigurer();
		cfg.setLocation(new ClassPathResource("conjur.properties"));
		return cfg;
	}
	

}
