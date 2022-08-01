package com.cyberark.conjur.clientapp.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cyberark.conjur.api.Conjur;
import com.cyberark.conjur.api.Credentials;
import com.cyberark.conjur.configclient.domain.ConjurAuthParam;

public class ConjurConnectionManager {

	private static Logger logger = LoggerFactory.getLogger(ConjurConnectionManager.class);

	private static ConjurConnectionManager conjurConnectionInstance = null;

	private static Conjur conjur;

	private ConjurConnectionManager() {

		getConnection();

	}

	private static Conjur getConnection(ConjurAuthParam conjurParam) {

		logger.info("Inside Connection Manager get Connection with AuthParam");
		//Credentials credentials = new Credentials(conjurParam.getConjurAccount(),
		 //conjurParam.getConjurApiKey().trim(),
		 //conjurParam.getConjurApplianceUrl());
		//conjur = new Conjur(credentials);

		try {
			logger.info("Account >>>" + conjurParam.getConjurAccount());
			System.setProperty("CONJUR_ACCOUNT", conjurParam.getConjurAccount());
			System.setProperty("CONJUR_APPLIANCE_URL", conjurParam.getConjurApplianceUrl());
			System.setProperty("CONJUR_AUTHN_LOGIN", conjurParam.getConjurAuthLogin());
			System.setProperty("CONJUR_AUTHN_API_KEY", conjurParam.getConjurApiKey().trim());

			conjur = new Conjur();
			
			logger.info("Connection with Conjur is successful" + conjur);

		} catch (Exception e) {

			logger.error(e.getMessage());
		}
		return conjur;
	}

	private static Conjur getConnection() {

		logger.info("Inside Connection Manager get Connection");

		try {

			conjur = new Conjur();

			logger.info("Connection with Conjur is successful" + conjur);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return conjur;
	}

	/**
	 * method to create instance of class and checking for multiple threads.
	 * 
	 * @return unique instance of class.
	 * @throws CertificateException
	 */
	public static Conjur getInstance(ConjurAuthParam authParam) {
		try {
			if (conjur == null) {
				synchronized (Conjur.class) {
					if (conjur == null) {
						conjur = getConnection(authParam);
					}
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		logger.info("Conjur connection object" + conjur);
		return conjur;
	}

	public static Conjur getInstance() {
		try {
			if (conjur == null) {
				synchronized (Conjur.class) {
					if (conjur == null) {
						conjur = getConnection();
					}
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		logger.info("Conjur connection object" + conjur);
		return conjur;
	}

}
