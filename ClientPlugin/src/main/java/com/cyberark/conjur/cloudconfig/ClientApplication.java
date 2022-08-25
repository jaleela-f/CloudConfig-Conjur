package com.cyberark.conjur.cloudconfig;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@SpringBootApplication
@RestController
public class ClientApplication implements CommandLineRunner{

	private Logger logger = LoggerFactory.getLogger(ClientApplication.class);

	@Value("${jenkins-app.dbUserName}")
	private String userName;

	@Value("${jenkins-app.dbPassword}")
	private String password;

	@Value("${jenkins-app.dbUrl}")
	private String url;

	
	public static void main(String[] args) {
		SpringApplication.run(ClientApplication.class, args);

	}

	@RequestMapping("/getProperty")
	public String getAuthParams() {
		logger.info("Rocking the Libray usage :) !");
		logger.info("Property >>>>>>>" + userName);
		logger.info("Property >>>>>>>" + password);
		logger.info("Property >>>>>>>>" + url);
		return String
				.valueOf("REtrieved Secrets :" + "DBUSERNAME =" + userName + " \n Password =" + password + " \n Url =" + url);
		
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		logger.info("Rocking the Libray usage :) !");
		logger.info("Property >>>>>>>" + userName);
		logger.info("Property >>>>>>>" + password);
		logger.info("Property >>>>>>>>" + url);
		
	}
	

}
