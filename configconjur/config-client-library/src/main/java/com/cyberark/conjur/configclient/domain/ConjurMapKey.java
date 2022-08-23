package com.cyberark.conjur.configclient.domain;

import java.util.Iterator;
import java.util.Properties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@Configuration
//@ConfigurationProperties("conjur.mapping")
public class ConjurMapKey {
	
	@Autowired
	Environment env;
	
	public ConjurMapKey()
	{
		System.out.println("Inside ConjurMapKey");
	}
	
	public Properties getApplicationProperties()
	{
		final Properties properties = new Properties();
		
		
		
		return properties;
	}
	
	
			

}
