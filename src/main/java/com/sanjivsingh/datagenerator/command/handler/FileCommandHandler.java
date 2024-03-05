package com.sanjivsingh.datagenerator.command.handler;

import java.io.File;
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
 * The Class FileCommandHandler.
 *
 * @author Sanjiv.Singh
 */
public class FileCommandHandler extends CommandHandleTemplate {

	/**
	 * Generate.
	 *
	 * @param argsMap the args map
	 */
	@Override
	public void generate(Map<String, String> argsMap) {

		int numberOfRecords = 0;
		String inputDir = null;
		String outputDir = null;
		String seperator = null;

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
		if (argsMap.containsKey(CommandConstants.OUTPUT_DIR)) {
			outputDir = argsMap.get(CommandConstants.OUTPUT_DIR);
		} else {
			outputDir = PropertiesManager.getConfigurations().getValue(
					"user.output.directory");
		}
		if (argsMap.containsKey(CommandConstants.SEPERATOR)) {
			seperator = argsMap.get(CommandConstants.SEPERATOR);
		} else {
			seperator = PropertiesManager.getConfigurations().getValue(
					"default.records.seperator");
		}

		// create big data generator instance
		ContextualDataGeneratorEngine bdGenerator = new ContextualDataGeneratorEngine(
				inputDir);

		PersistanceConnectorFactory pcf = new PersistanceConnectorFactory();
		Map<String, String> conProperties = new HashMap<String, String>();
		conProperties.put(ConnectorConstants.FILE_INPUT_DIR, inputDir);
		conProperties.put(ConnectorConstants.FILE_OUTPUT_DIR, outputDir);
		conProperties.put(ConnectorConstants.FILE_SEPERATOR, seperator);
		pcf.load(ConnectorTypes.FILE, conProperties);

		GenericPersistanceConnector fileConnectorInstance = pcf
				.getConnectorInstance();
		bdGenerator.generateData(numberOfRecords, fileConnectorInstance);

		// print generated file
		File f = new File(outputDir); // current directory
		File[] files = f.listFiles();
		for (File file : files) {
			if (file.isFile() && file.getPath().endsWith(".txt")) {
				System.out.print("\n    Generated file : " + file.getPath());
			}
		}
	}

}
