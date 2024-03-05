package com.sanjivsingh.datagenerator.common;

import com.sanjivsingh.datagenerator.core.datatype.DataTypeBuilder;

/**
 * Manages resources configured.
 * 
 * @author Sanjiv.Singh
 * 
 */
public class ResourceFactory {

	/** The resource factory. */
	private static ResourceFactory resourceFactory;

	/**
	 * Gets the resource factory.
	 *
	 * @return the resource factory
	 */
	public static ResourceFactory getResourceFactory() {
		if (null == resourceFactory) {
			resourceFactory = new ResourceFactory();
		}
		return resourceFactory;
	}

	/**
	 * Gets the data type builder.
	 *
	 * @return the data type builder
	 */
	public DataTypeBuilder getDataTypeBuilder() {
		return new DataTypeBuilder();
	}

}
