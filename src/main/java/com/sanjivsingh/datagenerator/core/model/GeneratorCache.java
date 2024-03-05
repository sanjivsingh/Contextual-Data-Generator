package com.sanjivsingh.datagenerator.core.model;

import java.util.HashMap;
import java.util.Map;

import com.sanjivsingh.datagenerator.core.connector.model.BaseConnectorInput;

public class GeneratorCache {

	private static GeneratorCache cache;
	private Map<String, HashMap<Integer, BaseConnectorInput<String, Object>>> map;

	private GeneratorCache() {
		super();
		map = new HashMap<>();
	}

	public static GeneratorCache getInstance() {
		if (null == cache) {
			cache = new GeneratorCache();
		}
		return cache;
	}

	public Map<String, HashMap<Integer, BaseConnectorInput<String, Object>>> getMap() {
		return map;
	}

}
