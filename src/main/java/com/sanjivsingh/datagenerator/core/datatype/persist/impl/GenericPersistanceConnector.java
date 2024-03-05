package com.sanjivsingh.datagenerator.core.datatype.persist.impl;

import java.util.List;

import com.sanjivsingh.datagenerator.core.datatype.persist.ConnectorTypes;
import com.sanjivsingh.datagenerator.core.datatype.persist.LifeCycleManager;
import com.sanjivsingh.datagenerator.core.datatype.persist.PersistanceConnector;

public abstract class GenericPersistanceConnector implements
		PersistanceConnector<String>, LifeCycleManager {

	protected ConnectorTypes connectorType;

	public GenericPersistanceConnector(ConnectorTypes connectorType) {
		super();
		this.connectorType = connectorType;
	}

	@Override
	public void initialize() {
		// TODO Auto-generated method stub
	}

	@Override
	public boolean isThreadSafe() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
	}

	@Override
	public abstract void generateAndSave(List<String> records);

}
