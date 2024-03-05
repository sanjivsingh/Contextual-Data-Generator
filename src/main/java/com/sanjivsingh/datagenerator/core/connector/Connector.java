package com.sanjivsingh.datagenerator.core.connector;

import com.sanjivsingh.datagenerator.core.connector.model.BaseConnectorInput;
import com.sanjivsingh.datagenerator.core.connector.model.TStatus;
import com.sanjivsingh.datagenerator.exception.DataGenException;

/**
 * @author Sanjiv.Singh
 * 
 */
public interface Connector {

	public TStatus connect() throws DataGenException;

	public boolean publish(BaseConnectorInput<String, Object> inputList)
			throws DataGenException;

	public void close() throws DataGenException;

}
