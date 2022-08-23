package com.cyberark.conjur.clientapp.service;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.EnumerablePropertySource;
import org.springframework.core.env.PropertySource;

import com.cyberark.conjur.api.Conjur;
import com.cyberark.conjur.api.Variables;
import com.cyberark.conjur.clientapp.core.ConjurConnectionManager;
import com.cyberark.conjur.clientapp.core.ConjurPropertySource;
import com.cyberark.conjur.clientapp.env.ConjurMapProperty;
import com.cyberark.conjur.configclient.domain.ConjurAuthParam;

public class CustomPropertySourceChain extends PropertyProcessorChain {

	private Logger logger = LoggerFactory.getLogger(CustomPropertySourceChain.class);

	private PropertyProcessorChain chain;
	private Map<String, Object> systemConfigMap = new HashMap<>();
	
	@Autowired(required = false)
	private ConjurAuthParam conjurParam;// = new ConjurAuthParam();

	private Conjur conjur = null;
	Variables var;

	// @Autowired
	// private ConjurPropertySource propertySource = new ConjurPropertySource();
	public CustomPropertySourceChain() {
		super("customProeprtySource");
		System.out.println("Calling CustomPropertysource Chain");
	}

	@Override
	public void setNextChain(PropertyProcessorChain nextChain) {
		// TODO Auto-generated method stub
		this.chain = nextChain;

	}

	@Override
	public String[] getPropertyNames() {
		// TODO Auto-generated method stub
		return new String[0];
	}

	@Override
	public Object getProperty(String key) {
		String value = null;
		try {

			conjur = ConjurConnectionManager.getInstance(conjurParam);
			if (null != conjur) {

				var = conjur.variables();
				logger.info("Key >>" + key);
				key = ConjurMapProperty.getInstance().mapProperty(key);

				value = var.retrieveSecret(key.replace(".", "/"));
				System.out.println("Value from Vault >>>>"+value);

			}

		} catch (Exception e) {
			// logger.error("Error connecting to Conjur Vault" + e.getMessage());

		}
		// logger.info("Value>>>>" + value);
		return value;
	}

	

}
