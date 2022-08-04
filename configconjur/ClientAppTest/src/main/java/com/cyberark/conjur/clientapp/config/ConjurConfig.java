package com.cyberark.conjur.clientapp.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;

import com.cyberark.conjur.clientapp.core.ConjurPropertySource;

@Configuration
public class ConjurConfig {
	
	private Logger logger = LoggerFactory.getLogger(ConjurConfig.class);

	@Bean
	@Primary
	public ConjurPropertySource getPropertySource() {
		return new ConjurPropertySource();
	}

}
