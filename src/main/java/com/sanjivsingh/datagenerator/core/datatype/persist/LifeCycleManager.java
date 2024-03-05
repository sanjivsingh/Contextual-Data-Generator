package com.sanjivsingh.datagenerator.core.datatype.persist;


public interface LifeCycleManager {

	/**
	 * Initialize configured client.
	 */
	void initialize();

	/**
	 * Returns true if client is thread safe, else false.
	 * 
	 * @return true if client is thread safe.
	 */
	boolean isThreadSafe();

	/**
	 * Unloads/destroy configured client instance.
	 */
	void destroy();

}
