package com.avira.common;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

/**
 * Class file provides Utility for property reader
 * 
 * @author chetanit
 *
 */
public class PropertyReader {
	private Properties properties;
	private String fileName;

	/**
	 * Taking file name
	 * 
	 * @param fileName
	 */

	public PropertyReader(String fileName) {
		this.fileName = fileName;
		this.properties = new Properties();
		init();
	}

	/**
	 * Loads the files
	 */
	private void init() {
		try {
			properties.load(new FileInputStream(new File(fileName)));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * get the files
	 * 
	 * @param key
	 * @return
	 * @throws NullPointerException
	 */
	public String get(String key) throws NullPointerException {

		if (properties.getProperty(key) == null)
			throw new NullPointerException(key + "|You are reading invalid property from the properties files." + // selenium,
					"Please check the passed spelling argument / check the availability for argument in messages properties files");
		else
			return properties.getProperty(key);
	}
}
