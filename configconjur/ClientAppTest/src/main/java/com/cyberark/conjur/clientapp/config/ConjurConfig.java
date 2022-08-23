package com.cyberark.conjur.clientapp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.cyberark.conjur.configclient.domain.ConjurConfigParam;

@Configuration
public class ConjurConfig {

	@Bean
	public ConjurConfigParam getConjurConfigParam()
	{
		return new ConjurConfigParam();
	}
	
	
}
