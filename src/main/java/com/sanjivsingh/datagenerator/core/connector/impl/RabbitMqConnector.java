package com.sanjivsingh.datagenerator.core.connector.impl;

import java.io.IOException;

import com.rabbitmq.client.Address;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.MessageProperties;
import com.sanjivsingh.datagenerator.core.connector.Connector;
import com.sanjivsingh.datagenerator.core.connector.model.BaseConnectorInput;
import com.sanjivsingh.datagenerator.core.connector.model.TStatus;
import com.sanjivsingh.datagenerator.exception.DataGenException;

/**
 * @author Sanjiv.Singh
 * 
 */
public class RabbitMqConnector implements Connector {

	private String queueIp = null;
	private String queueName = null;
	private int port;

	private Connection connection = null;
	private Channel channel = null;

	public String getQueueIp() {
		return queueIp;
	}

	public void setQueueIp(String queueIp) {
		this.queueIp = queueIp;
	}

	public String getQueueName() {
		return queueName;
	}

	public void setQueueName(String queueName) {
		this.queueName = queueName;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	@Override
	public TStatus connect() throws DataGenException {
		String[] ips = queueIp.split(System.getProperty("line.separator"));
		Address[] addresses = new Address[ips.length];
		for (int i = 0; i < ips.length; i++) {
			addresses[i] = new Address(ips[i]);
		}
		TStatus tStatus = new TStatus();
		tStatus.setStatus(true);

		ConnectionFactory factory = new ConnectionFactory();
		try {
			connection = factory.newConnection(addresses);
			channel = connection.createChannel();
			boolean durable = true;
			channel.queueDeclare(queueName, durable, false, false, null);
		} catch (IOException e) {
			throw new DataGenException("error connecting to the queue"
					+ e.getMessage());
		}
		return tStatus;
	}

	@Override
	public boolean publish(BaseConnectorInput<String, Object> inputList)
			throws DataGenException {
		try {
			channel.basicPublish("", queueName,
					MessageProperties.PERSISTENT_TEXT_PLAIN,
					QUtil.convert2ByteArray(inputList.getInput().get("data")));
		} catch (IOException e) {
			throw new DataGenException("error sending data to the queue"
					+ e.getMessage());
		}
		return true;
	}

	@Override
	public void close() {
		try {
			channel.close();
			connection.close();
		} catch (IOException e) {
			throw new DataGenException("error closing the queue"
					+ e.getMessage());
		}
	}

}
