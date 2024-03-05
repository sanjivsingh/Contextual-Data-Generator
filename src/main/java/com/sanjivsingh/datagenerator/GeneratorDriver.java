package com.sanjivsingh.datagenerator;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sanjivsingh.datagenerator.core.datatype.persist.ConnectorConstants;
import com.sanjivsingh.datagenerator.core.datatype.persist.ConnectorTypes;
import com.sanjivsingh.datagenerator.core.datatype.persist.ParserModel;
import com.sanjivsingh.datagenerator.core.datatype.persist.PersistanceConnectorFactory;
import com.sanjivsingh.datagenerator.core.datatype.persist.RecordParser;
import com.sanjivsingh.datagenerator.core.datatype.persist.TypeTypeValue;
import com.sanjivsingh.datagenerator.core.datatype.persist.impl.CassandraPersistanceConnector;
import com.sanjivsingh.datagenerator.core.datatype.persist.impl.GenericPersistanceConnector;
import com.sanjivsingh.datagenerator.core.datatype.persist.impl.ListPersistanceConnector;
import com.sanjivsingh.datagenerator.core.datatype.persist.impl.RDBMSPersistanceConnector;
import com.sanjivsingh.datagenerator.core.engine.ContextualDataGeneratorEngine;

public class GeneratorDriver {

	public static void main(String[] args) {

		final String inputDir = "/home/sanjivsingh/BDDG/input/";
		final String outputDir = "/home/sanjivsingh/BDDG/output/";

		// create big data generator instance
		ContextualDataGeneratorEngine bdGenerator = new ContextualDataGeneratorEngine(inputDir);
		int numberOfRecords = 10000;

		// generate data : format 1
		fileExample(outputDir, bdGenerator, numberOfRecords);

		// generate data : format 2
		// listExample(bdGenerator, numberOfRecords);

		// generate data : format 3
		rabbitmqExample(bdGenerator, numberOfRecords);

		// generate data : format 4
		// cassandraExample(bdGenerator, numberOfRecords);

		// generate data : format 5
		// rdbmsExample(bdGenerator, numberOfRecords);

		List<String> toBeMatched = new ArrayList<String>();
		toBeMatched.add("ad");
		toBeMatched.add("abd");
		toBeMatched.add("acd");
		toBeMatched.add("abcd");
		// match sample input data with User expression

		System.out.println("-------------------------------------------");
		System.out.println("Matching given inputs :");
		System.out.println("-------------------------------------------");
		for (String match : toBeMatched) {
			if (bdGenerator.matchData(match)) {
				System.out.println("Matched    : " + match);
			} else {
				System.out.println("Un-Matched : " + match);
			}
		}
	}

	private static void cassandraExample(ContextualDataGeneratorEngine bdGenerator, int numberOfRecords) {
		PersistanceConnectorFactory pcf = new PersistanceConnectorFactory();
		Map<String, String> conProperties = new HashMap<String, String>();
		conProperties.put(ConnectorConstants.CASSANDRA_HOST, "localhost");
		conProperties.put(ConnectorConstants.CASSANDRA_PORT, "9160");
		conProperties.put(ConnectorConstants.CASSANDRA_KEYSPACE, "KunderaExamples");
		conProperties.put(ConnectorConstants.CASSANDRA_COLUMNFAMILY, "users");
		pcf.load(ConnectorTypes.CASSANDRA, conProperties);

		CassandraPersistanceConnector cassandraConnectorInstance = (CassandraPersistanceConnector) pcf
				.getConnectorInstance();
		RecordParser recordParser = new RecordParser() {
			@Override
			public ParserModel parser(String inputRecord) {
				String aparams[] = inputRecord.replaceAll("\n", "").split(",");
				ParserModel parserModel = new ParserModel();
				if (aparams.length == 4) {
					parserModel.getMap().put("userId", new TypeTypeValue("Id", "String", aparams[0]));
					parserModel.getMap().put("first_name", new TypeTypeValue("Column", "String", aparams[1]));
					parserModel.getMap().put("last_name", new TypeTypeValue("Column", "String", aparams[2]));
					parserModel.getMap().put("city", new TypeTypeValue("Column", "String", aparams[3]));
				}
				return parserModel;
			}
		};
		cassandraConnectorInstance.setRecordParser(recordParser);
		bdGenerator.generateData(numberOfRecords, cassandraConnectorInstance);
	}

	private static void rdbmsExample(ContextualDataGeneratorEngine bdGenerator, int numberOfRecords) {

		String serverIP = "192.168.145.53";
		int port = 3009;
		String dataBaseName = "myDataBase";
		String tableName = "myTable";

		RDBMSPersistanceConnector foc = new RDBMSPersistanceConnector(serverIP, port, dataBaseName, tableName);

		RecordParser recordParser = new RecordParser() {
			@Override
			public ParserModel parser(String inputRecord) {
				String aparams[] = inputRecord.split(",");
				ParserModel parserModel = new ParserModel();
				if (aparams.length == 4) {

					parserModel.getMap().put("userId", new TypeTypeValue("Id", "String", aparams[0]));
					parserModel.getMap().put("first_name", new TypeTypeValue("Column", "String", aparams[0]));
					parserModel.getMap().put("last_name", new TypeTypeValue("Column", "String", aparams[0]));
					parserModel.getMap().put("city", new TypeTypeValue("Column", "String", aparams[0]));
				}
				return parserModel;
			}
		};
		foc.setRbbmdRecordParser(recordParser);
		bdGenerator.generateData(numberOfRecords, foc);
	}

	private static void fileExample(final String outputDir, ContextualDataGeneratorEngine bdGenerator,
			int numberOfRecords) {

		String seperator = "";

		PersistanceConnectorFactory pcf = new PersistanceConnectorFactory();
		Map<String, String> conProperties = new HashMap<String, String>();
		conProperties.put(ConnectorConstants.FILE_INPUT_DIR, bdGenerator.getInputDir());
		conProperties.put(ConnectorConstants.FILE_OUTPUT_DIR, outputDir);
		conProperties.put(ConnectorConstants.FILE_SEPERATOR, seperator);
		pcf.load(ConnectorTypes.FILE, conProperties);

		GenericPersistanceConnector fileConnectorInstance = pcf.getConnectorInstance();
		bdGenerator.generateData(numberOfRecords, fileConnectorInstance);

		// print generated file
		File f = new File(outputDir); // current directory
		File[] files = f.listFiles();
		for (File file : files) {
			if (file.isDirectory()) {
				System.out.print("directory:");
			} else {
				System.out.print("    Generated file : " + file.getPath());
			}
		}
	}

	private static void listExample(ContextualDataGeneratorEngine bdGenerator, int numberOfRecords) {
		PersistanceConnectorFactory pcf = new PersistanceConnectorFactory();
		Map<String, String> conProperties = null;

		pcf.load(ConnectorTypes.LIST, conProperties);
		ListPersistanceConnector listConnectorInstance = (ListPersistanceConnector) pcf.getConnectorInstance();

		bdGenerator.generateData(numberOfRecords, listConnectorInstance);
		if (null != listConnectorInstance.getRecords()) {
			System.out.println("Data Generated data :");
			for (String data : listConnectorInstance.getRecords()) {
				System.out.println(data);
			}
		}
	}

	private static void rabbitmqExample(ContextualDataGeneratorEngine bdGenerator, int numberOfRecords) {

		PersistanceConnectorFactory pcf = new PersistanceConnectorFactory();
		Map<String, String> conProperties = new HashMap<String, String>();
		conProperties.put(ConnectorConstants.RABBITMQ_HOST, "192.168.145.53");
		conProperties.put(ConnectorConstants.RABBITMQ_PORT, "3009");
		conProperties.put(ConnectorConstants.RABBITMQ_QUEUE, "myQueue");
		pcf.load(ConnectorTypes.RABBITMQ, conProperties);

		GenericPersistanceConnector rabbitConnectorInstance = pcf.getConnectorInstance();

		bdGenerator.generateData(numberOfRecords, rabbitConnectorInstance);
	}
}
