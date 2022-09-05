package com.cyberark.conjur.cloudconfig.env;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

public class ConjurConfigParam {
	@Value("${spring.config.location}")
	private String path;
	
	
	public ConjurConfigParam()
	{
		
	}
	@Autowired
	public void getConjurConnection(@Value("${CONJUR.ACCOUNT}") String account,
			@Value("${CONJUR.APPLIANCE_URL}") String url, @Value("${CONJUR.AUTHN_LOGIN}") String authLogin,
			@Value("${CONJUR.API_KEY}") String authApiKey)

	{
		System.out.println("Invoking ConjurConfigParam>>");
		System.setProperty("CONJUR_ACCOUNT", account);
		System.setProperty("CONJUR_APPLIANCE_URL", url);
		System.setProperty("CONJUR_AUTHN_LOGIN", authLogin);
		System.setProperty("CONJUR_AUTHN_API_KEY", authApiKey.trim());
		System.setProperty("CONJUR_PROPERTY_MAP", path);

	}

}
