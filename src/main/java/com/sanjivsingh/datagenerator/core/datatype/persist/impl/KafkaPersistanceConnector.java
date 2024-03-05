package com.sanjivsingh.datagenerator.core.datatype.persist.impl;

import java.util.List;

import com.sanjivsingh.datagenerator.core.datatype.persist.ConnectorTypes;

public class KafkaPersistanceConnector extends GenericPersistanceConnector {

	private String broker;
	private int port = 3099;
	private String topic;

	public KafkaPersistanceConnector(String broker, int port, String topic) {
		super(ConnectorTypes.KAFKA);
		this.broker = broker;
		this.port = port;
		this.topic = topic;
	}

	@Override
	public void generateAndSave(List<String> records) {
		// TODO Auto-generated method stub

	}

}
