package com.cyberark.configclient.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.core.env.Environment;

@SpringBootApplication
@RefreshScope

public class ConfigClientApp {

	public static void main(String[] args) {
		SpringApplication.run(ConfigClientApp.class, args);
	}

	@Autowired
	public void setEnv(Environment e) {

		System.out.println("Environment variable set");
		System.out.println(">>>>>" +e.getProperty("CONJUR.ACCOUNT"));
		System.out.println(">>>>>" + e.getProperty("CONJUR.APPLIANCE_URL"));
		System.out.println(">>>>>" + e.getProperty("CONJUR.AUTHN_LOGIN"));
		System.out.println(">>>>>" + e.getProperty("CONJUR.API_KEY"));
		System.out.println(">>>>>" + e.getProperty("CONJUR.AUTHN_TOKEN_FILE"));
		System.out.println(">>>>>" + e.getProperty("CONJUR.CERT_FILE "));
		System.out.println(">>>>>" + e.getProperty("CONJUR.SSL_CERTIFICATE"));

	}

}
