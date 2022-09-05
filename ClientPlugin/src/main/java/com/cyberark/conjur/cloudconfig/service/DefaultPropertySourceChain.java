package com.cyberark.conjur.cloudconfig.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cyberark.conjur.cloudconfig.env.ConjurMapProperty;

public class DefaultPropertySourceChain extends PropertyProcessorChain {

	private Logger logger = LoggerFactory.getLogger(DefaultPropertySourceChain.class);

	private PropertyProcessorChain chain;

	public DefaultPropertySourceChain() {

		super("defaultPropertySource");
		logger.info("Calling DefaultPropertysource Chain");

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
		key = ConjurMapProperty.getInstance().mapProperty(key);
		logger.info("Key >>"+key);
		if (value == null) {
			
			value = (String) this.chain.getProperty(key);
		}

		return value;
	}

}
