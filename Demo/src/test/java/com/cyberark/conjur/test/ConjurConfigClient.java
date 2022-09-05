package com.cyberark.conjur.test;


import static org.junit.Assert.assertNotNull;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.cyberark.conjur.clientapp.core.ConjurConnectionManager;
import com.cyberark.conjur.configclient.domain.ConjurAuthParam;
import com.cyberark.conjur.configclient.domain.ConjurConfigParam;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ConjurConfigClient {
	
	@Value("${jenkins-app.dbUserName}")
	private String userName;
	
	@Value("${jenkins-app.dbPassword}")
	private String password;
	
	@Value("${jenkins-app.dbUrl}")
	private String url;
	
	@Autowired
	private ConjurConfigParam configParam;
	
	@Autowired
	private ConjurAuthParam authParam;
	
	
	@Test
	public void contextLoad()
	{
		
		assertNotNull(ConjurConnectionManager.getInstance(authParam));
		
	}

}
