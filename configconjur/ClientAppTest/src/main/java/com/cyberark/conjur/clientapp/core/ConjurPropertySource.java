package com.cyberark.conjur.clientapp.core;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;

import com.cyberark.conjur.api.Conjur;
import com.cyberark.conjur.clientapp.util.ConjurPropertyLoaderUtil;
import com.cyberark.conjur.configclient.domain.ConjurAuthParam;

public class ConjurPropertySource implements EnvironmentAware {

	private Logger logger = LoggerFactory.getLogger(ConjurPropertySource.class);

	private Conjur conjur;

	private static ConjurPropertyLoaderUtil propertyLoader = new ConjurPropertyLoaderUtil();

	@Autowired
	private ConjurAuthParam conjurParam = new ConjurAuthParam();

	@Autowired
	Environment env;

	@Override
	public void setEnvironment(Environment environment) {
		this.env = environment;
	}

	public Environment getEnvironment() {
		return env;
	}

	public ConjurPropertySource() {

	}

	public Object getPropertyMethod(String key) {

		Map<String, Object> myMap = new HashMap<String, Object>();

		if (null == conjur) {

			conjur = ConjurConnectionManager.getInstance(conjurParam);
		}
		String result = null;
		try {

			result = conjur.variables().retrieveSecret(key.replace(".", "/"));
			//logger.info("Value inside PropertySource>>>" + result);
		} catch (Exception e) {
			e.getMessage();
		}
		return result;

	}

}
