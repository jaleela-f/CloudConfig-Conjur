package com.cyberark.conjur.clientapp.core;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;

import com.cyberark.conjur.api.Conjur;
import com.cyberark.conjur.clientapp.util.ConjurPropertyLoaderUtil;
import com.cyberark.conjur.configclient.domain.ConjurAuthParam;

public class ConjurPropertySource implements EnvironmentAware{
	
	private  Conjur conjur;

	private static ConjurPropertyLoaderUtil propertyLoader = new ConjurPropertyLoaderUtil();
	Map<String, Object> secretParams = new HashMap<String, Object>();
	
	@Autowired
	private ConjurAuthParam conjurParam;// = new ConjurAuthParam();
	
	@Autowired
	Environment env;

	@Override
	public void setEnvironment(Environment environment) {
		this.env = environment;
	}

	public Environment getEnvironment() {
		return env;
	}

	public ConjurPropertySource() {

	}
	
public Object getPropertyMethod() {

		
		Map<String, Object> myMap = new HashMap<String, Object>();
		
		
		if (null == conjur) {
				
			conjur = ConjurConnectionManager.getInstance(conjurParam);
		}
		String result = null;
			try {
			propertyLoader.readPropertiesFromFile();

			Set<Object> keySet = propertyLoader.getKey();
			System.out.println("Key from conjur >>>>"+conjur.toString());
			Iterator iter = keySet.iterator();
			String[] keys = new String[keySet.size()];
			String value1;
			String key;
			// ConjurPropertySource source = new ConjurPropertySource();
			while (iter.hasNext()) {
				key = (String) iter.next();
				System.out.println("Key available"+conjur);
				try {
				result = conjur.variables().retrieveSecret(key.replace(".", "/"));
				System.out.println("Value inside PropertySource>>>"+result);
				}catch(Exception ex)
				{
					System.out.println("Exception ex"+ex.getMessage());
				}

				myMap.put(key, result);
				

			}

			} catch (Exception e) {
			e.getMessage();
		}
		return myMap;

	}

}
