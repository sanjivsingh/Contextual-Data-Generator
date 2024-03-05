package com.sanjivsingh.datagenerator.core.model;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Sanjiv.Singh
 * 
 */
public class SMap {

	public Map<Integer, State> smap = new HashMap<Integer, State>();

	public State get(int index) {
		return smap.get(index);
	}

	public void put(int index, State state) {
		smap.put(index, state);
	}

}
