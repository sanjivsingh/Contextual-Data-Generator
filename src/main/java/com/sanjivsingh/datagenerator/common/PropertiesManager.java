package com.sanjivsingh.datagenerator.common;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import com.sanjivsingh.datagenerator.exception.DataGenRuntimeException;

/**
 * Manages the various properties and configurations.
 * 
 * @author Sanjiv.Singh
 * 
 */
public class PropertiesManager {

	/** The properties. */
	private Properties properties;

	/** The config properties. */
	private static PropertiesManager configProperties;
	
	/** The system properties. */
	private static PropertiesManager systemProperties;
	
	/** The message properties. */
	private static PropertiesManager messageProperties;
	
	/** The data type classes properties. */
	private static PropertiesManager dataTypeClassesProperties;

	/**
	 * Instantiates a new properties manager.
	 *
	 * @param propertyFileName the property file name
	 */
	private PropertiesManager(String propertyFileName) {
		try {
			properties = new Properties();
			InputStream in = Thread.currentThread().getContextClassLoader().getResourceAsStream(propertyFileName);
			properties.load(in);
		} catch (IOException e) {
			// TODO Handle it properly
			throw new DataGenRuntimeException("Property file " + propertyFileName + " not found.", e);
		}
	}

	/**
	 * Instantiates a new properties manager.
	 *
	 * @param in the in
	 */
	private PropertiesManager(InputStream in) {
		try {
			properties = new Properties();
			properties.load(in);
		} catch (IOException e) {
			// TODO Handle it properly
			throw new DataGenRuntimeException("Unable to load properties.");
		}
	}

	/**
	 * Returns the system configs.
	 *
	 * @return the configurations
	 */
	public static PropertiesManager getConfigurations() {
		if (configProperties != null) {
			return configProperties;
		} else {
			String configPropertiesPath = null;
			try {
				configPropertiesPath = PropertiesManager.getSystemProperties().getValue("configs.file.path");
				File file = new File(configPropertiesPath);
				if (file.isFile()) {
					configProperties = new PropertiesManager(new FileInputStream(file));
				} else {
					InputStream in = Thread.currentThread().getContextClassLoader().getResourceAsStream(configPropertiesPath);
					configProperties = new PropertiesManager(in);
				}
				return configProperties;
			} catch (FileNotFoundException e) {
				// TODO Handle it properly
				throw new DataGenRuntimeException("Property file " + configPropertiesPath + " not found.", e);
			}
		}
	}

	/**
	 * Returns the system properties (programmer specific properties).
	 *
	 * @return the system properties
	 */
	public static PropertiesManager getSystemProperties() {
		if (systemProperties != null) {
			return systemProperties;
		} else {
			systemProperties = new PropertiesManager("System.properties");
			return systemProperties;
		}
	}

	/**
	 * Returns config/property-value for a particular key.
	 *
	 * @param key the key
	 * @return the value
	 */
	public String getValue(String key) {
		return properties.getProperty(key);
	}

	/**
	 * Gets the message properties.
	 *
	 * @return the message properties
	 */
	public static PropertiesManager getMessageProperties() {
		if (messageProperties != null) {
			return messageProperties;
		} else {
			String msgPropertiesPath = null;
			try {
				msgPropertiesPath = PropertiesManager.getSystemProperties().getValue("messages.file.path");
				messageProperties = new PropertiesManager(new FileInputStream(msgPropertiesPath));
				return messageProperties;
			} catch (FileNotFoundException e) {
				// TODO Handle it properly
				throw new DataGenRuntimeException("Property file " + msgPropertiesPath + " not found.", e);
			}
		}
	}

	/**
	 * Gets the data type classes properties.
	 *
	 * @return the data type classes properties
	 */
	public static PropertiesManager getDataTypeClassesProperties() {
		if (dataTypeClassesProperties != null) {
			return dataTypeClassesProperties;
		} else {
			String dataTypeClassesPropertiesPath = null;
			try {
				dataTypeClassesPropertiesPath = PropertiesManager.getSystemProperties().getValue("datatype.classes.file.path");
				File file = new File(dataTypeClassesPropertiesPath);
				if (file.isFile()) {
					dataTypeClassesProperties = new PropertiesManager(new FileInputStream(file));
				} else {
					InputStream in = Thread.currentThread().getContextClassLoader().getResourceAsStream(dataTypeClassesPropertiesPath);
					dataTypeClassesProperties = new PropertiesManager(in);
				}
				return dataTypeClassesProperties;
			} catch (FileNotFoundException e) {
				// TODO Handle it properly
				throw new DataGenRuntimeException("Property file " + dataTypeClassesPropertiesPath + " not found.", e);
			}
		}
	}

}
