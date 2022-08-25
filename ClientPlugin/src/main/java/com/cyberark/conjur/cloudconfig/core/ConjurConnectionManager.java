package com.cyberark.conjur.cloudconfig.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.cyberark.conjur.api.Conjur;

public class ConjurConnectionManager {

	private static Logger logger = LoggerFactory.getLogger(ConjurConnectionManager.class);
	private static Conjur conjur;

	private ConjurConnectionManager() {

	}
	
	private static Conjur getConnection() {

		logger.info("Inside Connection Manager get Connection");
		try {
			conjur = new Conjur();
			logger.info("Connection with Conjur is successful");

		} catch (Exception ex) {
			logger.error("Error connecting to Conjur Vault"+ex.getMessage());
		}
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
		} catch (Exception ex) {
			logger.error("Error getting Conjur Instance"+ex.getStackTrace());
		}

		return conjur;
	}

}
