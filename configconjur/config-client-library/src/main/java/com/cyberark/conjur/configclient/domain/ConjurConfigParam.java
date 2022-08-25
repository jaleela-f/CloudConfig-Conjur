package com.cyberark.conjur.configclient.domain;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@Configuration

public class ConjurConfigParam {
	
	@Autowired
	private static Environment env;
	
	@Value("${CONJUR.ACCOUNT}")
	private String account;
	
	@Value("${CONJUR.API_KEY}")
	private String apiKey;
	
	@Value("${CONJUR.APPLIANCE_URL}") 
	private String url;
	
	@Value("${CONJUR.AUTHN_LOGIN}")
	private String login;
	
	@Value("${spring.config.location}")
	private String path;
	
	private List<String> keys = new ArrayList<String>();
	
	
	
	public static final String CONJUR_MAPPING = "conjur.mapping.";
	
	public ConjurConfigParam()
	{
		
	}
	
	@Bean
	public ConjurAuthParam  conjurAuthParam()
	
	{
		
		//System.out.println("Calling ConjurConfigParam>>>>"+env.getProperty(CONJUR_MAPPING+"dbUserName"));
		ConjurAuthParam conjurAuthParam = new ConjurAuthParam();
		conjurAuthParam.setConjurAccount(account);
		conjurAuthParam.setConjurApiKey(apiKey);
		conjurAuthParam.setConjurApplianceUrl(url);
		conjurAuthParam.setConjurAuthLogin(login);
		
		System.setProperty("CONJUR_ACCOUNT", account);
		System.setProperty("CONJUR_APPLIANCE_URL", url);
		System.setProperty("CONJUR_AUTHN_LOGIN", login);
		System.setProperty("CONJUR_AUTHN_API_KEY", apiKey.trim());
		System.setProperty("CONJUR_PROPERTY_MAP",path);
		
		
		return conjurAuthParam;
	}
	
	


}
