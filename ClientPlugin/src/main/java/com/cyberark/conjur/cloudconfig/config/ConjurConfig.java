package com.cyberark.conjur.cloudconfig.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import com.cyberark.conjur.cloudconfig.env.ConjurConfigParam;

@Configuration
public class ConjurConfig {

	@Bean
	public ConjurConfigParam getConjurConfigParam() {
		return new ConjurConfigParam();
	}
}
