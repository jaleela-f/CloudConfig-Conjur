package com.cyberark.conjur.configclient.domain;

import java.io.BufferedReader;
import java.io.FileReader;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConjurAuthParam {

	private static String conjurAccount;

	private static String conjurApplianceUrl;

	private static String conjurAuthLogin;

	private static String conjurApiKey;

	private static String conjurTokenFile;
	
	public ConjurAuthParam()
	{
		System.out.println("Inside ConjurAuthParam");
	}

	public String getConjurAccount() {
		return conjurAccount;
	}

	@Autowired
	public void setConjurAccount(@Value("${CONJUR.ACCOUNT}") String conjurAccount) {
		this.conjurAccount = conjurAccount;
	}

	public String getConjurApplianceUrl() {
		return conjurApplianceUrl;
	}

	@Autowired
	public void setConjurApplianceUrl(@Value("${CONJUR.APPLIANCE_URL}") String conjurApplianceUrl) {
		this.conjurApplianceUrl = conjurApplianceUrl;

	}

	public String getConjurAuthLogin() {
		return conjurAuthLogin;
	}

	@Autowired
	public void setConjurAuthLogin(@Value("${CONJUR.AUTHN_LOGIN}") String conjurAuthLogin) {
		this.conjurAuthLogin = conjurAuthLogin;
	}

	public String getConjurApiKey() {
		return conjurApiKey;
	}

	@Autowired
	public void setConjurApiKey(@Value("${CONJUR.API_KEY}") String conjurApiKey) {
		this.conjurApiKey = conjurApiKey;
	}

	public String getConjurTokenFile() {
		return conjurTokenFile;
	}

	@Autowired
	public void setConjurTokenFile(@Value("${CONJUR.AUTHN_TOKEN_FILE}") String conjurTokenFile) {

		this.conjurTokenFile = conjurTokenFile;

	}

}
