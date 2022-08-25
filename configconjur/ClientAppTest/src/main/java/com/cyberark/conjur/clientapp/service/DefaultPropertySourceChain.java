package com.cyberark.conjur.clientapp.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cyberark.conjur.clientapp.util.ServerConstant;


public class DefaultPropertySourceChain extends PropertyProcessorChain {

	private Logger logger = LoggerFactory.getLogger(DefaultPropertySourceChain.class);

	private PropertyProcessorChain chain;

	//private Map<String, Object> newMap = new HashMap<String, Object>();
	//private List<String> valueList = new ArrayList<String>();

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
		

		/*if((!(name.contains(ServerConstant.SPRING_PREFIX)))&&(!(name.contains(ServerConstant.SERVER_PREFIX)))&& 
				(!(name.contains(ServerConstant.ERROR_PREFIX))) && (!(name.contains(ServerConstant.HEALTH_PREFIX))) && 
				(!(name.contains(ServerConstant.ECRYPT_PREFIX))) && (!(name.contains(ServerConstant.SPRING_UTIL_PREFIX))))
		{*/
			
			if (value == null ) {
				value = (String) this.chain.getProperty(name);
			}
		//}
		

		return value;
	}

}
