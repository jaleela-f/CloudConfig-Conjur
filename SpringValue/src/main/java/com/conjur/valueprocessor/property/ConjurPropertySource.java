package com.conjur.valueprocessor.property;

import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.EnumerablePropertySource;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.core.env.PropertySource;
import org.springframework.core.env.StandardEnvironment;

public class ConjurPropertySource extends EnumerablePropertySource<Object> {

	private String name;

	public ConjurPropertySource() {
	super("conjurPropertySource");
		
		
	}

	

	@Override
	public Object getProperty(String key) {

		System.out.println("Enumerable Property Source>>>" + key);

		String result = null;
		if(key.contains("dbUserName")|| key.contains("dbPassword"))
		{
			result="working";
		}
		
		System.out.println("Enumerable Property Source>End>>"+result);
		return result;
	}



	@Override
	public String[] getPropertyNames() {
		// TODO Auto-generated method stub
		return new String[0];
	}

}
