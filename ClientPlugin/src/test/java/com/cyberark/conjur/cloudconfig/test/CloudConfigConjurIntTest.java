package com.cyberark.conjur.cloudconfig.test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.event.annotation.BeforeTestClass;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import com.cyberark.conjur.api.Conjur;
import com.cyberark.conjur.cloudconfig.config.ConjurConfig;
import com.cyberark.conjur.cloudconfig.core.ConjurConnectionManager;
import com.cyberark.conjur.cloudconfig.env.ConjurConfigParam;
import com.cyberark.conjur.cloudconfig.env.ConjurMapProperty;
import com.cyberark.conjur.cloudconfig.processor.ValueProcess;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes= {ConjurConfig.class,ValueProcess.class})
@TestPropertySource(properties = {"spring.config.location= file://${user.home}/config-repo/conjur.properties"})
public class CloudConfigConjurIntTest {
	
	@Value("${dbUserName}")
	private String dbUserName;
	
	@Value("${dbPassword}")
	private String dbPassword;
	
	@Value("${dbUrl}")
	private String dbUrl;
	
	@Value("${CONJUR.ACCOUNT}")
	private String account;
	
	@Value("${CONJUR.APPLIANCE_URL}")
	private String url;
	
	@Value("${CONJUR.AUTHN_LOGIN}")
	private String authLogin;
	
	@Value("${CONJUR.API_KEY}")
	private String apiKey;
	
	@Mock
	private ConjurConfigParam configParam;
	
	@Mock 
	private ConjurConnectionManager connectionMgr;
	
	@Mock
	private Conjur conjur;
	
	@Mock
	private ConjurMapProperty mapProperty;
	
	@Mock
	private ValueProcess valueProcess;
	
	
	@Test
	public void contextLoads()
	{
		
		
	}
	

	@Test
	@DisplayName("Testing Conjur Authentication Parameter from CLoud Config Server")
	public void testConjurAuthParam()
	{
		
		//assertEquals(account,System.getProperty("CONJUR.ACCOUNT"));
		assertNotNull(account);
		assertNotNull(apiKey);
		assertNotNull(url);
		assertNotNull(authLogin);
		
	}
	
	
	
	@Test
	public void testConjurConnection()
	{
		System.setProperty("CONJUR_ACCOUNT", account);
		System.out.println("Account >>>>"+account);
		System.setProperty("CONJUR_APPLIANCE_URL", url);
		System.setProperty("CONJUR_AUTHN_LOGIN", authLogin);
		System.setProperty("CONJUR_AUTHN_API_KEY", apiKey.trim());
	

		conjur = connectionMgr.getInstance();
		
		assertNotNull(conjur);
		
	}
	@Test
	public void testSecretValue()
	{
		assertNotNull(dbUserName);
		assertNotNull(dbPassword);
		assertNotNull(dbUrl);
		
	}
	@Test
	public void testCustomValue()
	{
		assertNotNull(dbUserName);
		assertNotNull(dbPassword);
		assertNotNull(dbUrl);
	}
	public void testWithNoSystemConjurParam()
	{
		
	}
	

}
