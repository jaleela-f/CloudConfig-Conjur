package com.cyberark.conjur.clientapp.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.cyberark.conjur.api.Conjur;
import com.cyberark.conjur.configclient.domain.ConjurAuthParam;

public class ConjurConnectionManager {

	private static Logger logger = LoggerFactory.getLogger(ConjurConnectionManager.class);

	private static Conjur conjur;

	private ConjurConnectionManager() {

	}

	private static Conjur getConnection(ConjurAuthParam conjurParam) {

		logger.info("Inside Connection Manager get Connection with AuthParam");

		try {

			conjur = new Conjur();

			logger.info("Connection with Conjur is successful");

		} catch (Exception e) {

			logger.error(e.getMessage());
		}
		return conjur;
	}

	private static Conjur getConnection() {

		logger.info("Inside Connection Manager get Connection");

		try {

			conjur = new Conjur();

			logger.info("Connection with Conjur is successful");

		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return conjur;
	}

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
			e.printStackTrace();
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
		} catch (Exception e) {

			e.printStackTrace();
		}

		return conjur;
	}

}
