package com.cyberark.conjur.clientapp.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.Environment;
import org.springframework.core.env.MapPropertySource;
import org.springframework.core.env.PropertySource;

import com.cyberark.conjur.clientapp.core.ConjurPropertySource;

public class CustomPropertySourceChain implements PropertyProcessorChain {
	
	private Logger logger = LoggerFactory.getLogger(CustomPropertySourceChain.class);

	private PropertyProcessorChain chain;
	private Map<String, Object> systemConfigMap = new HashMap<>();

	@Autowired
	private ConjurPropertySource propertySource = new ConjurPropertySource();


	private final String propertySourceName = "classpath:/conjur.properties";


	@Override
	public void setNextChain(PropertyProcessorChain nextChain) {
		// TODO Auto-generated method stub
		this.chain = nextChain;

	}

	@Override
	public Map<String, Object> getProperty(List<String> propertyKey, PropertySource ps) {
		// TODO Auto-generated method stub
		logger.info("Calling getproperty Method of CustomPropertySource");
		String value;
		for (String key : propertyKey) {
			value =  propertySource.getPropertyMethod(key).toString();
		//	System.out.println("Value>>>>"+value);
			systemConfigMap.put(key, value);
			
		}
		//System.out.println("SEcret value" + systemConfigMap);
		return systemConfigMap;

	}

}
