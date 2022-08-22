package com.cyberark.conjur.clientapp.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.EnumerablePropertySource;
import org.springframework.stereotype.Component;

import com.cyberark.conjur.api.Conjur;
import com.cyberark.conjur.api.Variables;
import com.cyberark.conjur.clientapp.env.ConjurMapProperty;
import com.cyberark.conjur.configclient.domain.ConjurAuthParam;

@Component
public class ConjurPropertySource extends EnumerablePropertySource<Object> {

	private Logger logger = LoggerFactory.getLogger(ConjurPropertySource.class);

	@Autowired(required = false)
	private ConjurAuthParam conjurParam;// = new ConjurAuthParam();

	private Conjur conjur = null;
	Variables var;

	public ConjurPropertySource() {
		super("conjurPropertySource");

	}

	@Override
	public String[] getPropertyNames() { // TODO Auto-generated method stub
		return new String[0];
	}

	@Override
	public Object getProperty(String key) { // TODO Auto-generated method stub
		String value = null;
		try {

			// if (key.contains("jenkins-app.dbUserName") ||
			// key.contains("jenkins-app.dbPassword")
			// || key.contains("jenkins-app.dbUrl")) {

			// if (null == conjur) {

			conjur = ConjurConnectionManager.getInstance(conjurParam);
			if (null != conjur) {
				var = conjur.variables();
				//logger.info("Key >>" + key);
				key = ConjurMapProperty.getInstance().mapProperty(key);
				logger.info("Key >>" + key);

				value = var.retrieveSecret(key.replace(".", "/"));
			}

			// }
			// }

		} catch (Exception e) {
			//logger.error("Error connecting to Conjur Vault" + e.getStackTrace());

		}
		//logger.info("Value>>>>" + value);
		return value;
	}

}
