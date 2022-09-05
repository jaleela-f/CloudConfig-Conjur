package com.cyberark.conjur.cloudconfig.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.env.EnumerablePropertySource;


public abstract class PropertyProcessorChain extends  EnumerablePropertySource<Object>{
	
	private static Logger logger = LoggerFactory.getLogger(PropertyProcessorChain.class);
	
	private PropertyProcessorChain processorChain;
	
	public PropertyProcessorChain(String name) {
		super("propertyProcessorChain");
		logger.info("Invoking Processor Chain");
		
	}
	public void setNextChain(PropertyProcessorChain processChain)
	{
		this.processorChain = new DefaultPropertySourceChain();
		CustomPropertySourceChain customPS = new CustomPropertySourceChain();
		processorChain.setNextChain(customPS);
	}
	
	@Override
	public Object getProperty(String key)
	{
		return key;
		
	}
	

}
