package com.cyberark.conjur.clientapp.service;

import java.util.List;
import java.util.Map;

import org.springframework.core.env.PropertySource;


public interface PropertyProcessorChain {
	
	void setNextChain(PropertyProcessorChain processChain);
	Map<String,Object> getProperty(List<String> key,PropertySource ps);
	

}
