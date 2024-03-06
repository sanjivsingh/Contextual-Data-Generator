package com.sanjivsingh.datagenerator.core.datatype;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import com.sanjivsingh.datagenerator.common.ResourceFactory;
import com.sanjivsingh.datagenerator.core.DataTypeParameters;
import com.sanjivsingh.datagenerator.core.connector.model.BaseConnectorInput;
import com.sanjivsingh.datagenerator.core.model.Expressions;
import com.sanjivsingh.datagenerator.core.model.ExpressssionValue;
import com.sanjivsingh.datagenerator.core.model.GeneratorCache;
import com.sanjivsingh.datagenerator.core.model.GeneratorContext;
import com.sanjivsingh.datagenerator.core.model.IScope;
import com.sanjivsingh.datagenerator.core.model.Symbals;
import com.sanjivsingh.datagenerator.core.util.DataUtil;
import com.sanjivsingh.datagenerator.exception.DataGenException;

/**
 * The Class DataGeneratorHandler.
 *
 * @author Sanjiv.Singh
 */
public class DataGeneratorHandler {

	/** The sym bol index. */
	private int symBolIndex = Symbals.MAX_SYMBOL_INDEX + 1;
	
	/** The input expression. */
	private String inputExpression;
	
	/** The expression. */
	private String expression;
	
	/** The generator context. */
	private GeneratorContext generatorContext = new GeneratorContext();

	/**
	 * Instantiates a new data generator handler.
	 *
	 * @param uniqueID the unique ID
	 * @param inputExpression the input expression
	 */
	public DataGeneratorHandler(String uniqueID, String inputExpression) {
		generatorContext.setUniqueID(uniqueID);
		this.inputExpression = inputExpression;
		this.expression = inputExpression;
	}

	/**
	 * Gets the formated expression.
	 *
	 * @return the formated expression
	 */
	public String getFormatedExpression() {
		expression = DataUtil.formatRegex(expression);
		// TODO replace DATA_TYPE place holders with symbols
		try {
			expression = format();
		} catch (Exception ex) {
			throw new DataGenException(
					"Error while parsing dataType Expression : " + expression);
		}

		// generate iteration tree...
		IScope iScope = generateIterationTree();
		generatorContext.setIterationTree(iScope);
		return expression;
	}

	/**
	 * Generate iteration tree.
	 *
	 * @return the i scope
	 */
	private IScope generateIterationTree() {

		// System.out.println(expression);
		int start = expression.length() - 1;
		int end = 0;
		IScope node = new IScope();
		node.symbol = -1;
		parseIt(start, end, node);

		return node;
	}

	/**
	 * Parses the it.
	 *
	 * @param start the start
	 * @param end the end
	 * @param node the node
	 */
	private void parseIt(int start, int end, IScope node) {

		if (start >= 0 && start >= end) {
			char chatAt = expression.charAt(start);
			// System.out.println(start + " and " + end + "  " + chatAt);
			int value = intValue(start);
			if (isStarPlus(start)) {
				// System.out.println("*+ found at + " + start);
				IScope child = createChild(node, chatAt);
				parseIt(start - 1, end, child);
			} else if (isStarPlus(start + 1)
					&& value > Symbals.MAX_SYMBOL_INDEX) {
				// System.out.println("DATATYPE* found at " + start);
				IScope child = createChild(node, chatAt);
				parseIt(start - 1, end, child.parent.parent);
			} else if (isStarPlus(start + 1)
					&& value == Symbals.MAX_SYMBOL_INDEX) {
				// System.out.println("}* found at " + start);
				int leftIndex = findLeftIndex(start, end);
				parseIt(start - 1, leftIndex, node);
				parseIt(leftIndex - 1, end, node.parent);
			} else if (value > Symbals.MAX_SYMBOL_INDEX) {
				createChild(node, chatAt);
				parseIt(start - 1, end, node);
			} else {
				parseIt(start - 1, end, node);
			}
		}
	}

	/**
	 * Int value.
	 *
	 * @param index the index
	 * @return the int
	 */
	private int intValue(int index) {
		char chatAt = expression.charAt(index);
		int value = (int) chatAt;
		return value;
	}

	/**
	 * Find left index.
	 *
	 * @param start the start
	 * @param end the end
	 * @return the int
	 */
	private int findLeftIndex(int start, int end) {
		int depth = 1;
		for (int i = start - 1; i >= end; i--) {
			int value = intValue(i);
			if (value == Symbals.R_PRAN_SYMBOL_INDEX) {
				depth++;
			} else if (value == Symbals.L_PRAN_SYMBOL_INDEX) {
				depth--;
			}
			if (depth == 0) {
				return i;
			}
		}
		return end;
	}

	/**
	 * Creates the child.
	 *
	 * @param current the current
	 * @param chatAt the chat at
	 * @return the i scope
	 */
	private IScope createChild(IScope current, char chatAt) {
		IScope child = new IScope();
		child.symbol = chatAt;
		child.parent = current;
		current.childs.add(child);
		return child;
	}

	/**
	 * Checks if is star plus.
	 *
	 * @param index the index
	 * @return true, if is star plus
	 */
	private boolean isStarPlus(int index) {
		if (index >= 0 && index < expression.length()) {
			char chatAt = expression.charAt(index);
			return (chatAt == Symbals.STAR || chatAt == Symbals.PLUS);
		}
		return false;
	}

	/**
	 * Sample generated data.
	 *
	 * @param recordIndex the record index
	 * @param str the str
	 * @return the string
	 */
	public String sampleGeneratedData(int recordIndex, String str) {

		// TODO replace symbols with random generated data
		Set<Integer> indexs = generatorContext.getIndexs();
		refreshContextForNewRecord();
		Object[] indexArray = indexs.toArray();
		Arrays.sort(indexArray);

		for (Object charIndex : indexArray) {
			if (haveId((Integer) charIndex)) {
				String randomData = getRandomDataForDateType(recordIndex,
						(Integer) charIndex);
				str = replaceAll(str,
						String.valueOf(Character.toChars((Integer) charIndex)),
						randomData);
			} else {
				while (str.contains(String.valueOf(Character
						.toChars((Integer) charIndex)))) {
					String randomData = getRandomDataForDateType(recordIndex,
							(Integer) charIndex);
					str = replaceFirst(str, String.valueOf(Character
							.toChars((Integer) charIndex)), randomData);
				}
			}
		}
		return str;
	}

	/**
	 * Have id.
	 *
	 * @param index the index
	 * @return true, if successful
	 */
	private boolean haveId(Integer index) {
		String dataTypeExpression = generatorContext.getExpression(index);

		String id = DataUtil.getId(dataTypeExpression);
		if (null == id || id.equals("")) {
			return false;
		} else {
			return true;
		}
	}

	/**
	 * Refresh context for new record.
	 */
	private void refreshContextForNewRecord() {
		for (int index : generatorContext.getIndexs()) {
			generatorContext.addValue(index, null);
		}
	}

	/**
	 * Replace all.
	 *
	 * @param str the str
	 * @param find the find
	 * @param replace the replace
	 * @return the string
	 */
	private String replaceAll(String str, String find, String replace) {

		while (str.contains(find)) {
			int indexOf = str.indexOf(find);
			str = str.substring(0, indexOf) + replace
					+ str.substring(indexOf + 1, str.length());
		}
		return str;
	}

	/**
	 * Replace first.
	 *
	 * @param str the str
	 * @param find the find
	 * @param replace the replace
	 * @return the string
	 */
	private String replaceFirst(String str, String find, String replace) {

		if (str.contains(find)) {
			int indexOf = str.indexOf(find);
			str = str.substring(0, indexOf) + replace
					+ str.substring(indexOf + 1, str.length());
		}
		return str;
	}

	/**
	 * Gets the random data for date type.
	 *
	 * @param recordIndex the record index
	 * @param charIndex the char index
	 * @return the random data for date type
	 */
	private String getRandomDataForDateType(int recordIndex, int charIndex) {

		String dataTypeExpression = generatorContext.getExpression(charIndex);

		String id = DataUtil.getId(dataTypeExpression);
		if (null == id || id.equals("")) {
			return getRandomValueThroughDataType(recordIndex, charIndex);
		} else {
			String valueById = getRandomValueFrombyId(id);
			if (null == valueById) {
				return getRandomValueThroughDataType(recordIndex, charIndex);
			} else {
				return valueById;
			}
		}
	}

	/**
	 * Gets the random value fromby id.
	 *
	 * @param id the id
	 * @return the random value fromby id
	 */
	private String getRandomValueFrombyId(String id) {
		ArrayList<ExpressssionValue> expresssionsById = generatorContext
				.getExpresssionById(id);
		for (ExpressssionValue eValue : expresssionsById) {
			if (eValue.getValue() != null) {
				return eValue.getValue();
			}
		}
		return null;
	}

	/**
	 * Gets the random value through data type.
	 *
	 * @param recordIndex the record index
	 * @param charIndex the char index
	 * @return the random value through data type
	 */
	private String getRandomValueThroughDataType(int recordIndex, int charIndex) {
		DataType dataType;
		try {
			dataType = ResourceFactory.getResourceFactory()
					.getDataTypeBuilder()
					.getDataType(charIndex, recordIndex, generatorContext);
		} catch (Exception e) {
			throw new DataGenException("No In-build DataType for Expression : "
					+ generatorContext.getExpression(charIndex));
		}

		String returnString = null;
		try {
			returnString = dataType.getDataTypeValue();
			generatorContext.addValue(charIndex, returnString);
			return returnString;
		} catch (Exception e) {
			throw new DataGenException(
					"Error in generating data for dataType expression : "
							+ generatorContext.getExpression(charIndex));
		}
	}

	/**
	 * Format.
	 *
	 * @return the string
	 */
	private String format() {

		int index = 0;
		while (index < expression.length()) {
			int indexOf = expression.indexOf(Expressions.START_EXPRESSION,
					index);
			if (indexOf == -1) {
				break;
			}
			int indexOf2 = expression.indexOf(Expressions.END_EXPRESSION,
					indexOf);
			String typeExpression = expression.substring(indexOf, indexOf2 + 2);
			Map<String, Object> map = DataUtil.getMap(typeExpression);
			String type = (String) map.get(DataTypeParameters.OPERATOR_TYPE);

			if (null != type && type.equals(Expressions.ALTERNATE_EXPRESSION)) {
				expression = expression.substring(0, indexOf)
						+ Symbals.ALTERNATE
						+ expression.substring(indexOf2 + 2,
								expression.length());
				indexOf2 = indexOf2 - typeExpression.length() + 1;
			} else if (null != type
					&& type.equals(Expressions.L_PRAN_EXPRESSION)) {
				expression = expression.substring(0, indexOf)
						+ Symbals.L_PRAN
						+ expression.substring(indexOf2 + 2,
								expression.length());
				indexOf2 = indexOf2 - typeExpression.length() + 1;
			} else if (null != type
					&& type.equals(Expressions.R_PRAN_EXPRESSION)) {
				expression = expression.substring(0, indexOf)
						+ Symbals.R_PRAN
						+ expression.substring(indexOf2 + 2,
								expression.length());
				indexOf2 = indexOf2 - typeExpression.length() + 1;
			} else if (null != type && type.equals(Expressions.STAR_EXPRESSION)) {
				expression = expression.substring(0, indexOf)
						+ Symbals.STAR
						+ expression.substring(indexOf2 + 2,
								expression.length());
				indexOf2 = indexOf2 - typeExpression.length() + 1;
				String range = (String) map
						.get(DataTypeParameters.OPERATOR_RANGE);
				updateCacheForOperationIteration(Symbals.STAR_SYMBOL_INDEX,
						range);
			} else if (null != type && type.equals(Expressions.PLUS_EXPRESSION)) {
				expression = expression.substring(0, indexOf)
						+ Symbals.PLUS
						+ expression.substring(indexOf2 + 2,
								expression.length());
				indexOf2 = indexOf2 - typeExpression.length() + 1;
				String range = (String) map
						.get(DataTypeParameters.OPERATOR_RANGE);
				updateCacheForOperationIteration(Symbals.PLUS_SYMBOL_INDEX,
						range);
			} else if (null != type
					&& type.equals(Expressions.QUESTION_MARK_EXPRESSION)) {
				expression = expression.substring(0, indexOf)
						+ Symbals.QUESTION_MARK
						+ expression.substring(indexOf2 + 2,
								expression.length());
				indexOf2 = indexOf2 - typeExpression.length() + 1;
				String range = (String) map
						.get(DataTypeParameters.OPERATOR_RANGE);
				updateCacheForOperationIteration(Symbals.QUES_SYMBOL_INDEX,
						range);
			} else {
				int temp = symBolIndex++;
				char c = Character.toChars(temp)[0];
				expression = expression.substring(0, indexOf)
						+ c
						+ expression.substring(indexOf2 + 2,
								expression.length());
				indexOf2 = indexOf2 - typeExpression.length() + 1;
				ExpressssionValue addedExpression = generatorContext
						.addExpression(temp, typeExpression);
				generatorContext.addExpresssionById(
						DataUtil.getId(addedExpression.getExpression()),
						addedExpression);
			}
			index = indexOf2 + 1;
		}
		return expression;
	}

	/**
	 * Update cache for operation iteration.
	 *
	 * @param charIndex the char index
	 * @param range the range
	 */
	private void updateCacheForOperationIteration(int charIndex, String range) {
		HashMap<Integer, BaseConnectorInput<String, Object>> hashMap = GeneratorCache
				.getInstance().getMap().get(generatorContext.getUniqueID());

		if (null != hashMap) {
			BaseConnectorInput<String, Object> charIndexMap = hashMap
					.get(charIndex);

			if (null != charIndexMap) {

				ArrayList<String> ranges = (ArrayList<String>) charIndexMap
						.getInput().get("ranges");
				if (null != ranges) {
					ranges.add(range);
				} else {
					ranges = new ArrayList<>();
					ranges.add(range);
					charIndexMap.getInput().put("ranges", ranges);
				}
			} else {
				charIndexMap = new BaseConnectorInput<String, Object>();

				ArrayList<String> ranges = new ArrayList<>();
				ranges.add(range);
				charIndexMap.getInput().put("ranges", ranges);

				// update BaseConnectorInput for charIndex
				hashMap.put(charIndex, charIndexMap);
			}
		} else {
			hashMap = new HashMap<>();
			BaseConnectorInput<String, Object> charIndexMap = new BaseConnectorInput<String, Object>();

			ArrayList<String> ranges = new ArrayList<>();
			ranges.add(range);
			charIndexMap.getInput().put("ranges", ranges);

			// update BaseConnectorInput for charIndex
			hashMap.put(charIndex, charIndexMap);

			// update cache for uniqueId
			GeneratorCache.getInstance().getMap()
					.put(generatorContext.getUniqueID(), hashMap);
		}
	}

	/**
	 * Gets the generator context.
	 *
	 * @return the generator context
	 */
	public GeneratorContext getGeneratorContext() {
		return generatorContext;
	}

	/**
	 * Sets the generator context.
	 *
	 * @param generatorContext the new generator context
	 */
	public void setGeneratorContext(GeneratorContext generatorContext) {
		this.generatorContext = generatorContext;
	}
}
