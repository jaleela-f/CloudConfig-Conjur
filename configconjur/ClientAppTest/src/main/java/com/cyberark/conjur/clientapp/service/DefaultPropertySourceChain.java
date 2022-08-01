package com.cyberark.conjur.clientapp.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.Environment;
import org.springframework.core.env.MapPropertySource;
import org.springframework.core.env.PropertySource;

public class DefaultPropertySourceChain implements PropertyProcessorChain {
	
	private Logger logger = LoggerFactory.getLogger(DefaultPropertySourceChain.class);

	private PropertyProcessorChain chain;

	private Map<String, Object> newMap = new HashMap<String, Object>();
	private List<String> valueList = new ArrayList<String>();

	@Override
	public void setNextChain(PropertyProcessorChain nextChain) {
		// TODO Auto-generated method stub
		this.chain = nextChain;

	}

	@Override
	public Map<String, Object> getProperty(List<String> propertyKey, PropertySource ps) {
		logger.info("Insde Get Property for DefaultPropertySourceChain");
		String value;

		for (String key : propertyKey) {
			// value = (String) ps.getProperty(key);
			newMap.put(key, ps.getProperty(key));
			//System.out.println("New MAp value" + newMap);

		}
		for (Map.Entry<String, Object> secret : newMap.entrySet()) {
			value = secret.getValue().toString();

			if (value != null && !value.isBlank()) {
				valueList.add(value);
			}

		}
		//System.out.println("Value List in default PropertySource" + valueList.size());
		if (!(valueList.size() > 0)) {
			newMap =this.chain.getProperty(propertyKey, ps);

		} 
		return newMap;

	}

	

}
