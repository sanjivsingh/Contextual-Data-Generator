package com.sanjivsingh.datagenerator.core.connector;

import com.sanjivsingh.datagenerator.core.connector.model.BaseConnectorInput;
import com.sanjivsingh.datagenerator.core.connector.model.TStatus;
import com.sanjivsingh.datagenerator.exception.DataGenException;

/**
 * The Interface Connector.
 *
 * @author Sanjiv.Singh
 */
public interface Connector {

	/**
	 * Connect.
	 *
	 * @return the t status
	 * @throws DataGenException the data gen exception
	 */
	public TStatus connect() throws DataGenException;

	/**
	 * Publish.
	 *
	 * @param inputList the input list
	 * @return true, if successful
	 * @throws DataGenException the data gen exception
	 */
	public boolean publish(BaseConnectorInput<String, Object> inputList)
			throws DataGenException;

	/**
	 * Close.
	 *
	 * @throws DataGenException the data gen exception
	 */
	public void close() throws DataGenException;

}
