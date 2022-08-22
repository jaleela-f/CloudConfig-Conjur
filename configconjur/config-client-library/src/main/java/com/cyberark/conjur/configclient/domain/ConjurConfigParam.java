package com.cyberark.conjur.configclient.domain;

import java.io.UnsupportedEncodingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConjurConfigParam {
	
	@Value("${CONJUR.ACCOUNT}")
	private String account;
	
	@Value("${CONJUR.API_KEY}")
	private String apiKey;
	
	@Value("${CONJUR.APPLIANCE_URL}") 
	private String url;
	
	@Value("${CONJUR.AUTHN_LOGIN}")
	private String login;
	
	@Value("${map.key.property.location}")
	private String path;
	
	public ConjurConfigParam()
	{
		
	}
	
	@Bean
	public ConjurAuthParam conjurAuthParam()
	
	{
		
		System.out.println("Calling ConjurConfigParam>>>>"+path);
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
