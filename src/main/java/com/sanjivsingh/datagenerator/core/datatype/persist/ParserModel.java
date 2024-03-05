package com.sanjivsingh.datagenerator.core.datatype.persist;

import java.util.HashMap;
import java.util.Map;

public class ParserModel {

	private Map<String, TypeTypeValue> map = new HashMap<>();

	public Map<String, TypeTypeValue> getMap() {
		return map;
	}

	public void setMap(Map<String, TypeTypeValue> map) {
		this.map = map;
	}

}
