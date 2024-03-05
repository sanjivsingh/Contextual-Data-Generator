package com.sanjivsingh.datagenerator.core.connector.model;

import java.util.HashMap;

/**
 * @author Sanjiv.Singh
 * 
 */
public class BaseConnectorInput<K, V> {

	private HashMap<K, V> input = new HashMap<K, V>();

	public HashMap<K, V> getInput() {
		return input;
	}

	public void setInput(HashMap<K, V> input) {
		this.input = input;
	}	

}
