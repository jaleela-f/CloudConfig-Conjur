package com.cyberark.conjur.clientapp.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.env.EnumerablePropertySource;
import org.springframework.core.env.PropertySource;

public class DefaultPropertySourceChain extends PropertyProcessorChain {

	private Logger logger = LoggerFactory.getLogger(DefaultPropertySourceChain.class);

	private PropertyProcessorChain chain;

	private Map<String, Object> newMap = new HashMap<String, Object>();
	private List<String> valueList = new ArrayList<String>();

	public DefaultPropertySourceChain() {

		super("defaultPropertySource");
		System.out.println("Calling DefaultPropertysource Chain");

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
	public Object getProperty(String name) {
		// TODO Auto-generated method stub

		String value = null;
		System.out.println("Value inside Default" + value);

		
			if (value == null) {
				value = (String) this.chain.getProperty(name);
			}
		

		return value;
	}

}
