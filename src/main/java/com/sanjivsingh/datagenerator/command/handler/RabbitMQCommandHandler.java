package com.sanjivsingh.datagenerator.command.handler;

import java.util.HashMap;
import java.util.Map;

import com.sanjivsingh.datagenerator.command.CommandConstants;
import com.sanjivsingh.datagenerator.command.CommandHandleTemplate;
import com.sanjivsingh.datagenerator.common.PropertiesManager;
import com.sanjivsingh.datagenerator.core.datatype.persist.ConnectorConstants;
import com.sanjivsingh.datagenerator.core.datatype.persist.ConnectorTypes;
import com.sanjivsingh.datagenerator.core.datatype.persist.PersistanceConnectorFactory;
import com.sanjivsingh.datagenerator.core.datatype.persist.impl.GenericPersistanceConnector;
import com.sanjivsingh.datagenerator.core.engine.ContextualDataGeneratorEngine;

/**
 * The Class RabbitMQCommandHandler.
 *
 * @author Sanjiv.Singh
 */
public class RabbitMQCommandHandler extends CommandHandleTemplate {

	/**
	 * Generate.
	 *
	 * @param argsMap the args map
	 */
	@Override
	public void generate(Map<String, String> argsMap) {

		int numberOfRecords = 0;
		String inputDir = null;
		String host = "localhost";
		String port = "3009";
		String queue = null;

		if (argsMap.containsKey(CommandConstants.NUMBER_OF_RECORDS)) {
			numberOfRecords = Integer.parseInt(argsMap
					.get(CommandConstants.NUMBER_OF_RECORDS));
		} else {
			numberOfRecords = Integer.parseInt(PropertiesManager
					.getConfigurations().getValue("default.numberforrecords"));
		}
		if (argsMap.containsKey(CommandConstants.INPUT_DIR)) {
			inputDir = argsMap.get(CommandConstants.INPUT_DIR);
		} else {
			inputDir = PropertiesManager.getConfigurations().getValue(
					"user.input.directory");
		}
		if (argsMap.containsKey(CommandConstants.HOST)) {
			host = argsMap.get(CommandConstants.HOST);
		}
		if (argsMap.containsKey(CommandConstants.PORT)) {
			port = argsMap.get(CommandConstants.PORT);
		}
		if (argsMap.containsKey(CommandConstants.QUEUE)) {
			queue = argsMap.get(CommandConstants.QUEUE);
		}

		// create big data generator instance
		ContextualDataGeneratorEngine bdGenerator = new ContextualDataGeneratorEngine(
				inputDir);

		PersistanceConnectorFactory pcf = new PersistanceConnectorFactory();
		Map<String, String> conProperties = new HashMap<String, String>();
		conProperties.put(ConnectorConstants.RABBITMQ_HOST, host);
		conProperties.put(ConnectorConstants.RABBITMQ_PORT, port);
		conProperties.put(ConnectorConstants.RABBITMQ_QUEUE, queue);
		pcf.load(ConnectorTypes.RABBITMQ, conProperties);

		GenericPersistanceConnector rabbitConnectorInstance = pcf
				.getConnectorInstance();

		bdGenerator.generateData(numberOfRecords, rabbitConnectorInstance);
	}

}
