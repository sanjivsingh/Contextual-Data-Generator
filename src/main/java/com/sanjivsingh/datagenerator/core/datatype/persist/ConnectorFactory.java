package com.sanjivsingh.datagenerator.core.datatype.persist;

import java.util.Map;

import com.sanjivsingh.datagenerator.core.datatype.persist.impl.GenericPersistanceConnector;

public interface ConnectorFactory {

	/**
	 * Load.
	 * 
	 * @param ConnectorTypes
	 *            the Connector Types
	 */
	void load(ConnectorTypes connectorTypes, Map<String, String> puProperties);

	/**
	 * Instantiate and returns connector instance
	 * 
	 * @return connector instance.
	 */
	GenericPersistanceConnector getConnectorInstance();

}
