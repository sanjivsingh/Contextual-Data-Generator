package com.sanjivsingh.datagenerator.core.datatype.persist.impl;

import java.util.List;

import com.sanjivsingh.datagenerator.core.datatype.persist.ConnectorTypes;

public class ListPersistanceConnector extends GenericPersistanceConnector {

	private List<String> records = null;

	@Override
	public void generateAndSave(List<String> records) {
		this.records = records;
	}

	public ListPersistanceConnector() {
		super(ConnectorTypes.LIST);
	}

	public List<String> getRecords() {
		return records;
	}

}
