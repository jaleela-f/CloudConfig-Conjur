package com.cyberark.conjur;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.cyberark.conjur.configclient.domain.ConjurAuthParam;

@SpringBootApplication
@RestController
public class ClientApplication {

	private Logger logger = LoggerFactory.getLogger(ClientApplication.class);

	@Value("${jenkins-app.dbUserName}")
	private String userName;

	@Value("${jenkins-app.dbPassword}")
	private String password;

	@Value("${jenkins-app.dbUrl}")
	private String url;

	@Autowired
	private ConjurAuthParam authParam;

	public static void main(String[] args) {
		SpringApplication.run(ClientApplication.class, args);

	}

	@RequestMapping("/getAuthParam")
	public String getAuthParams() {
		logger.info("Rocking the Libray usage :) !");
		String conurAccount = authParam.getConjurAccount();
		String conjurApiKey = authParam.getConjurApiKey();
		String conjurUrl = authParam.getConjurApplianceUrl();
		String conjurLogin = authParam.getConjurAuthLogin();

		logger.info("Property >>>>>>>" + userName);
		logger.info("Property >>>>>>>" + password);
		logger.info("Property >>>>>>>>" + url);

		logger.info("Conjur Account>>>" + conurAccount);
		logger.info("Conjur Url>>>>>" + conjurApiKey);
		logger.info("Conjur Login>>>>>" + conjurUrl);
		logger.info("Conjur API Key >>>>>." + conjurLogin);

		return String
				.valueOf("REtrieved Secrets :" + "DBUSERNAME =" + userName + "Password =" + password + "Url =" + url);
	}

}
