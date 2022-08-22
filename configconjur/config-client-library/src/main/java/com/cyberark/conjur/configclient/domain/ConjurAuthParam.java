package com.cyberark.conjur.configclient.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;


public class ConjurAuthParam {

	private  String conjurAccount;

	private  String conjurApplianceUrl;

	private  String conjurAuthLogin;

	private  String conjurApiKey;

	private  String conjurTokenFile;
	
	public ConjurAuthParam()
	{
		System.out.println("Inside ConjurAuthParam");
	}

	public String getConjurAccount() {
		return conjurAccount;
	}

	
	public void setConjurAccount( String conjurAccount) {
		this.conjurAccount = conjurAccount;
	}

	public String getConjurApplianceUrl() {
		return conjurApplianceUrl;
	}

	
	public void setConjurApplianceUrl( String conjurApplianceUrl) {
		this.conjurApplianceUrl = conjurApplianceUrl;

	}

	public String getConjurAuthLogin() {
		return conjurAuthLogin;
	}

	
	public void setConjurAuthLogin( String conjurAuthLogin) {
		this.conjurAuthLogin = conjurAuthLogin;
	}

	public String getConjurApiKey() {
		return conjurApiKey;
	}

	
	public void setConjurApiKey( String conjurApiKey) {
		this.conjurApiKey = conjurApiKey;
	}

	public String getConjurTokenFile() {
		return conjurTokenFile;
	}

	
	public void setConjurTokenFile( String conjurTokenFile) {

		this.conjurTokenFile = conjurTokenFile;

	}

	
	public String toString()
	{
		return "Property [ Account ="+conjurAccount +"Url =" +conjurApplianceUrl +"]";
	}

}

