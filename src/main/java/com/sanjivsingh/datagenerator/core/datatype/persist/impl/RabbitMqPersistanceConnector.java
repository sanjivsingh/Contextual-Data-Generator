package com.sanjivsingh.datagenerator.core.datatype.persist.impl;

import java.util.HashMap;
import java.util.List;

import com.sanjivsingh.datagenerator.core.connector.impl.RabbitMqConnector;
import com.sanjivsingh.datagenerator.core.connector.model.BaseConnectorInput;
import com.sanjivsingh.datagenerator.core.datatype.persist.ConnectorTypes;

public class RabbitMqPersistanceConnector extends GenericPersistanceConnector {

	private String host;
	private int port = 3099;
	private String queueName;

	private RabbitMqConnector rmq;

	public RabbitMqPersistanceConnector(String host, int port, String queueName) {
		super(ConnectorTypes.RABBITMQ);
		this.host = host;
		this.port = port;
		this.queueName = queueName;
	}

	@Override
	public void initialize() {
		rmq = new RabbitMqConnector();
		rmq.setQueueIp(host);
		rmq.setQueueName(queueName);
		rmq.setPort(port);

		rmq.connect();
	}

	@Override
	public void generateAndSave(List<String> records) {

		// Prepare BaseComponentInput for publish
		BaseConnectorInput<String, Object> intputBaseInput = new BaseConnectorInput<>();
		HashMap<String, Object> inputMap = new HashMap<String, Object>();
		intputBaseInput.setInput(inputMap);

		for (int i = 0; i < records.size(); i++) {
			// set the input records
			inputMap.put("data", records.get(i));
			// Trigger publish
			rmq.publish(intputBaseInput);
		}

	}

	@Override
	public void destroy() {
		rmq.close();

	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public String getQueueName() {
		return queueName;
	}

	public void setQueueName(String queueName) {
		this.queueName = queueName;
	}

}
