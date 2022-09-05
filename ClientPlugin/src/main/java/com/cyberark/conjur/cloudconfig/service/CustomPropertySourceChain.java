package com.cyberark.conjur.cloudconfig.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.cyberark.conjur.api.Conjur;
import com.cyberark.conjur.api.Variables;
import com.cyberark.conjur.cloudconfig.core.ConjurConnectionManager;
import com.cyberark.conjur.cloudconfig.env.ConjurMapProperty;

public class CustomPropertySourceChain extends PropertyProcessorChain {

	private Logger logger = LoggerFactory.getLogger(CustomPropertySourceChain.class);

	private PropertyProcessorChain chain;
	private Conjur conjur = null;
	private Variables var;

	public CustomPropertySourceChain() {
		super("customProeprtySource");
		logger.info("Calling CustomPropertysource Chain");
	}

	@Override
	public void setNextChain(PropertyProcessorChain nextChain) {
		this.chain = nextChain;

	}

	@Override
	public String[] getPropertyNames() {
		return new String[0];
	}

	@Override
	public Object getProperty(String key) {
		String value = null;
		try {

			conjur = ConjurConnectionManager.getInstance();
			if (null != conjur) {

				var = conjur.variables();
				key = ConjurMapProperty.getInstance().mapProperty(key);
				value = var.retrieveSecret(key.replace(".", "/"));
				System.out.println("Value from Custom Property"+value);
			}

		} catch (Exception e) {
			
		}

		return value;
	}

}
