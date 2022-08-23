package com.cyberark.conjur.clientapp.service;

import java.util.List;
import java.util.Map;

import org.springframework.core.env.EnumerablePropertySource;
import org.springframework.core.env.PropertySource;
import org.springframework.core.env.PropertySources;


public abstract class PropertyProcessorChain extends  EnumerablePropertySource<Object>{
	
	private PropertyProcessorChain processorChain;
	
	public PropertyProcessorChain(String name) {
		super("propertyProcessorChain");
		
	}
	public void setNextChain(PropertyProcessorChain processChain)
	{
		this.processorChain = new DefaultPropertySourceChain();

		CustomPropertySourceChain customPS = new CustomPropertySourceChain();
		processorChain.setNextChain(customPS);
	}
	
	@Override
	public Object getProperty(String name)
	{
		return name;
		
	}
	

}
