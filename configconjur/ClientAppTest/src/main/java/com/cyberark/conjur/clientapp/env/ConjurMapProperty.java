package com.cyberark.conjur.clientapp.env;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


/**
 * 
 * This class loads the external configured conjur.properties file and resolves
 * the keys values defined in properties file.
 *
 */

public class ConjurMapProperty {

	private static Properties props = new Properties();

	private static ConjurMapProperty uniqueInstance = new ConjurMapProperty();
	
	
	private String path = System.getProperty("CONJUR_PROPERTY_MAP");
	
	public static final String CONJUR_MAPPING = "conjur.mapping.";

	private ConjurMapProperty() {
		System.out.println("Property file>>>>"+path);

		InputStream propsFile = ConjurMapProperty.class.getResourceAsStream(path);

		if (propsFile != null) {
			try {
				props.load(propsFile);
				System.out.println("External Property>>>"+props.toString());
			} catch (IOException e) {
				e.printStackTrace();
			}

			try {
				propsFile.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

	/**
	 * 
	 * @return unique instance of class.
	 */
	public static ConjurMapProperty getInstance() {
		return uniqueInstance;
	}

	/**
	 * 
	 * @param name - key define at given property file.
	 * @return - corresponding value of key defined at given property file.
	 */
	public String mapProperty(String name) {
		//System.out.println("Received key>>>"+name);
		String mapped = props.getProperty(CONJUR_MAPPING + name);
		//System.out.println("Mapped key from property file"+mapped);
		return mapped != null ? mapped : name;
	}
}
