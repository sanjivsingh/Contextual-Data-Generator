package com.sanjivsingh.datagenerator.common;

import com.sanjivsingh.datagenerator.core.datatype.DataTypeBuilder;

/**
 * Manages resources configured.
 * 
 * @author Sanjiv.Singh
 * 
 */
public class ResourceFactory {

	private static ResourceFactory resourceFactory;

	public static ResourceFactory getResourceFactory() {
		if (null == resourceFactory) {
			resourceFactory = new ResourceFactory();
		}
		return resourceFactory;
	}

	public DataTypeBuilder getDataTypeBuilder() {
		return new DataTypeBuilder();
	}

}
