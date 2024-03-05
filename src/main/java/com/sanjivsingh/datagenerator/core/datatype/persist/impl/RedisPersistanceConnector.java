package com.sanjivsingh.datagenerator.core.datatype.persist.impl;

import java.util.List;

import com.sanjivsingh.datagenerator.core.datatype.persist.ConnectorTypes;

public class RedisPersistanceConnector extends GenericPersistanceConnector {

	public RedisPersistanceConnector() {
		super(ConnectorTypes.REDIS);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void generateAndSave(List<String> records) {
		// TODO Auto-generated method stub

	}

}
