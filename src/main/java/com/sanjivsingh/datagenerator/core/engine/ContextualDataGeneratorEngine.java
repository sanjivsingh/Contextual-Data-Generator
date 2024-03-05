package com.sanjivsingh.datagenerator.core.engine;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import com.sanjivsingh.datagenerator.common.PropertiesManager;
import com.sanjivsingh.datagenerator.core.datatype.DGFileReadContentHandler;
import com.sanjivsingh.datagenerator.core.datatype.DataGeneratorHandler;
import com.sanjivsingh.datagenerator.core.datatype.persist.impl.GenericPersistanceConnector;
import com.sanjivsingh.datagenerator.core.model.State;
import com.sanjivsingh.datagenerator.core.util.RandomUtil;
import com.sanjivsingh.datagenerator.exception.DataGenException;

/**
 * @author Sanjiv.Singh
 * 
 */
public class ContextualDataGeneratorEngine {

	private ThompsonAlgorithm ta;
	private State start;
	private DataGeneratorHandler dth;
	private Set<String> generatedPatterns;

	private static boolean byRange = true;

	private static int batchSize = Integer.parseInt(PropertiesManager.getConfigurations().getValue("batch.size"));

	private String inputDir;

	public ContextualDataGeneratorEngine() {
		super();
		if (null == inputDir) {
			this.inputDir = PropertiesManager.getConfigurations().getValue("user.input.directory");
		}
		initiate();
	}

	public ContextualDataGeneratorEngine(String inputDir) {
		this.inputDir = inputDir;
		initiate();
	}

	private void initiate() {

		String recordFile = PropertiesManager.getConfigurations().getValue("user.input.expression.filename");

		DGFileReadContentHandler dgFileHanlder = new DGFileReadContentHandler();
		String expression = dgFileHanlder.handleReadFile(inputDir + recordFile);

		// System.out.println("-----------------------------");
		// System.out.println("Input : ");
		// System.out.println("-----------------------------");
		// System.out.println(expression);
		// System.out.println("-----------------------------");

		String uniqueID = UUID.randomUUID().toString();

		dth = new DataGeneratorHandler(uniqueID, expression);
		expression = dth.getFormatedExpression();

		// System.out.println("Expression : ");
		// System.out.println("-----------------------------");
		// System.out.println(expression);
		// System.out.println("-----------------------------");

		// Create Thompson Aglorithm Instance
		ta = new ThompsonAlgorithm(uniqueID);

		// regex To Postfix conversion
		String postfixExp = ta.regexToPostfix(expression);
		if (null == postfixExp) {
			throw new DataGenException("Error in converting Input Expresssion to Postfix: " + expression);
		} else {
			// System.out.println("Postfix     : " + postfixExp);
		}

		// postfix To NFA conversion
		start = ta.postfixToNFA(postfixExp);
		if (start == null) {
			throw new DataGenException("Error in converting Postfix to NFA : " + postfixExp);
		}

		// generated Patterns
		GenertorFiniteStateMachine gfsm;
		if (byRange) {
			gfsm = new GeneratorFromFiniteStateMachineByRange();
			generatedPatterns = gfsm.generateData(start, ta.getSmap());
		} else {
			gfsm = new GeneratorFromFiniteStateMachineByPreviousCount();
			generatedPatterns = gfsm.generateData(start, ta.getSmap());
		}

		// System.out.println("created BigDataGeneratorEngine instance ["
		// + uniqueID + "]at : " + DataUtil.getDateString(new Date()));

	}

	public void generateData(final int numberOfRecords, GenericPersistanceConnector genericPersistanceConnector) {
		// System.out.println("Generating data....");

		// initialise connector
		genericPersistanceConnector.initialize();

		// set numberOfRecords in dth generatorContext
		dth.getGeneratorContext().setTotalRecords(numberOfRecords);

		// generate records and save
		int index = 1;
		int full = numberOfRecords / batchSize;
		int half = numberOfRecords % batchSize;

		for (int i = 1; i <= full; i++) {
			// System.out.println("generating index  : " + index);
			List<String> records = generateRecords(batchSize, index);
			// System.out.println("generated records : " + (i * batchSize)
			// + " at : " + DataUtil.getDateString(new Date()));
			genericPersistanceConnector.generateAndSave(records);
			// System.out.println("saved records : " + (i * tempNumberOfRecords)
			// + " at : " + DataUtil.getDateString(new Date()));
			index = index + batchSize;
		}
		if (half > 0) {
			// System.out.println("generating index  : " + index);
			List<String> records = generateRecords(half, index);
			// System.out.println("generated records : " + (numberOfRecords)
			// + " at : " + DataUtil.getDateString(new Date()));
			genericPersistanceConnector.generateAndSave(records);
			// System.out.println("saved records : " + (numberOfRecords)
			// + " at : " + DataUtil.getDateString(new Date()));
			index = index + half;
		}

		// close connector
		genericPersistanceConnector.destroy();

	}

	private List<String> generateRecords(int tempNumberOfRecords, int index) {
		List<String> records = null;
		if (null != generatedPatterns) {
			records = new ArrayList<>();
			final Object[] array = generatedPatterns.toArray();

			for (int i = 0; i < tempNumberOfRecords; i++) {
				int nextInt = RandomUtil.nextInt(0, array.length - 1);
				int recordIndex = index + i;
				records.add(dth.sampleGeneratedData(recordIndex, (String) array[nextInt]));
			}
		} else {
			System.out.println("no data");
		}
		return records;
	}

	public boolean matchData(String toBeMatched) {
		return ta.match(start, toBeMatched);
	}

	public String getInputDir() {
		return inputDir;
	}

	public void setInputDir(String inputDir) {
		this.inputDir = inputDir;
	}

}
