package com.cyberark.conjur.cloudconfig.env;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import org.springframework.util.ResourceUtils;

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
		System.out.println("Inside ConjurMapProperty" + path);

		try {

			File file = ResourceUtils.getFile(path);
			InputStream in = new FileInputStream(file);
			props.load(in);

		} catch (IOException e) {
			System.out.println(e.getMessage());
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

		String mapped = props.getProperty(CONJUR_MAPPING + name);
		return mapped != null ? mapped : name;
	}

}
