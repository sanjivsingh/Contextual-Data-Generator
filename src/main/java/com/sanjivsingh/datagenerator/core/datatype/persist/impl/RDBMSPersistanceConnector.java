package com.sanjivsingh.datagenerator.core.datatype.persist.impl;

import java.util.List;

import com.sanjivsingh.datagenerator.core.datatype.persist.ConnectorTypes;
import com.sanjivsingh.datagenerator.core.datatype.persist.ParserModel;
import com.sanjivsingh.datagenerator.core.datatype.persist.RecordParser;

public class RDBMSPersistanceConnector extends GenericPersistanceConnector {

	private String serverIP;
	private int port;
	private String dataBaseName;
	private String tableName;

	private RecordParser rbbmdRecordParser;

	public RDBMSPersistanceConnector(String serverIP, int port,
			String dataBaseName, String tableName) {
		super(ConnectorTypes.RDBMS);
		this.serverIP = serverIP;
		this.port = port;
		this.dataBaseName = dataBaseName;
		this.tableName = tableName;
	}

	@Override
	public void generateAndSave(List<String> records) {
		// make dbms connection

		for (String inputRecord : records) {
			// processes and save the record
			ParserModel parser = rbbmdRecordParser.parser(inputRecord);
		}

		// close dbms connection
	}

	public String getServerIP() {
		return serverIP;
	}

	public void setServerIP(String serverIP) {
		this.serverIP = serverIP;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public String getDataBaseName() {
		return dataBaseName;
	}

	public void setDataBaseName(String dataBaseName) {
		this.dataBaseName = dataBaseName;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public RecordParser getRbbmdRecordParser() {
		return rbbmdRecordParser;
	}

	public void setRbbmdRecordParser(RecordParser rbbmdRecordParser) {
		this.rbbmdRecordParser = rbbmdRecordParser;
	}

}
