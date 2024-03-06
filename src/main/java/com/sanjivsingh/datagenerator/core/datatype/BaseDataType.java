package com.sanjivsingh.datagenerator.core.datatype;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.sanjivsingh.datagenerator.core.DataTypeDefaults;
import com.sanjivsingh.datagenerator.core.DataTypeParameters;
import com.sanjivsingh.datagenerator.core.connector.model.BaseConnectorInput;
import com.sanjivsingh.datagenerator.core.model.GeneratorCache;
import com.sanjivsingh.datagenerator.core.model.GeneratorContext;
import com.sanjivsingh.datagenerator.core.util.DataUtil;
import com.sanjivsingh.datagenerator.core.util.RandomUtil;

/**
 * The Class BaseDataType.
 *
 * @author Sanjiv.Singh
 */
public abstract class BaseDataType implements DataType {

	/** The generator context. */
	protected GeneratorContext generatorContext;
	
	/** The char index. */
	protected int charIndex;
	
	/** The record index. */
	protected int recordIndex;

	/** The id. */
	protected String id;
	
	/** The group. */
	protected String group;

	/** The limit. */
	protected int limit = -1;
	
	/** The padding. */
	protected String padding = "right|-1| ";

	/** The pratio. */
	protected int pratio = 0;
	
	/** The plist. */
	protected ArrayList<String> plist = new ArrayList<String>();

	/** The parameter map. */
	private Map<String, Object> parameterMap;

	/**
	 * Instantiates a new base data type.
	 *
	 * @param charIndex the char index
	 * @param recordIndex the record index
	 * @param generatorContext the generator context
	 */
	public BaseDataType(int charIndex, int recordIndex,
			GeneratorContext generatorContext) {
		super();
		this.charIndex = charIndex;
		this.recordIndex = recordIndex;
		this.generatorContext = generatorContext;
		this.parameterMap = DataUtil.getMap(this.generatorContext
				.getExpression(charIndex));
		if (this.parameterMap.containsKey(DataTypeParameters.ID_FIELD)) {
			this.id = (String) this.parameterMap
					.get(DataTypeParameters.ID_FIELD);
		}
		if (this.parameterMap.containsKey(DataTypeParameters.GROUP)) {
			this.group = (String) this.parameterMap
					.get(DataTypeParameters.GROUP);
		}
		if (this.parameterMap.containsKey(DataTypeParameters.LIMIT)) {
			this.limit = getInt(parameterMap, DataTypeParameters.LIMIT);
		}
		if (this.parameterMap.containsKey(DataTypeParameters.PADDING)) {
			this.padding = (String) this.parameterMap
					.get(DataTypeParameters.PADDING);
		}
		if (this.parameterMap.containsKey(DataTypeParameters.PRATIO)) {
			this.pratio = getInt(parameterMap, DataTypeParameters.PRATIO);
		}
		if (this.parameterMap.containsKey(DataTypeParameters.PLIST)) {
			String listString = (String) this.parameterMap
					.get(DataTypeParameters.PLIST);
			String[] values = listString
					.split(DataTypeDefaults.VALUE_SEPERATOR);
			plist = new ArrayList<String>();
			for (String value : values)
				plist.add(value);
		}
	}

	/**
	 * Gets the boolean.
	 *
	 * @param parameterMap the parameter map
	 * @param parameter the parameter
	 * @return the boolean
	 */
	public static boolean getBoolean(Map<String, Object> parameterMap,
			String parameter) {
		String value = (String) parameterMap.get(parameter);
		boolean flag = Boolean.parseBoolean(value);
		return flag;
	}

	/**
	 * Gets the int.
	 *
	 * @param parameterMap the parameter map
	 * @param parameter the parameter
	 * @return the int
	 */
	public static int getInt(Map<String, Object> parameterMap, String parameter) {
		String value = (String) parameterMap.get(parameter);
		int flag = Integer.parseInt(value);
		return flag;
	}

	/**
	 * Gets the long.
	 *
	 * @param parameterMap the parameter map
	 * @param parameter the parameter
	 * @return the long
	 */
	public static long getLong(Map<String, Object> parameterMap,
			String parameter) {
		String value = (String) parameterMap.get(parameter);
		long flag = Long.parseLong(value);
		return flag;
	}

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * Sets the id.
	 *
	 * @param id the new id
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * Gets the group.
	 *
	 * @return the group
	 */
	public String getGroup() {
		return group;
	}

	/**
	 * Sets the group.
	 *
	 * @param group the new group
	 */
	public void setGroup(String group) {
		this.group = group;
	}

	/**
	 * Gets the parameter map.
	 *
	 * @return the parameter map
	 */
	public Map<String, Object> getParameterMap() {
		return parameterMap;
	}

	/**
	 * Sets the parameter map.
	 *
	 * @param parameterMap the parameter map
	 */
	public void setParameterMap(Map<String, Object> parameterMap) {
		this.parameterMap = parameterMap;
	}

	/**
	 * Gets the record index.
	 *
	 * @return the record index
	 */
	public int getRecordIndex() {
		return recordIndex;
	}

	/**
	 * Sets the record index.
	 *
	 * @param recordIndex the new record index
	 */
	public void setRecordIndex(int recordIndex) {
		this.recordIndex = recordIndex;
	}

	/**
	 * Gets the limit.
	 *
	 * @return the limit
	 */
	public int getLimit() {
		return limit;
	}

	/**
	 * Sets the limit.
	 *
	 * @param limit the new limit
	 */
	public void setLimit(int limit) {
		this.limit = limit;
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

	/**
	 * Gets the random value.
	 *
	 * @return the random value
	 */
	public abstract String getRandomValue();

	/**
	 * Gets the data type value.
	 *
	 * @return the data type value
	 */
	@Override
	public String getDataTypeValue() {

		String dataTypeValue = null;
		if (limit > 0 || pratio > 0) {
			HashMap<Integer, BaseConnectorInput<String, Object>> hashMap = GeneratorCache
					.getInstance().getMap().get(generatorContext.getUniqueID());

			if (null != hashMap) {
				BaseConnectorInput<String, Object> charIndexMap = hashMap
						.get(charIndex);

				if (null != charIndexMap) {

					// generate random value based on cache size and limit
					ArrayList<String> values = (ArrayList<String>) charIndexMap
							.getInput().get("values");

					int size = values.size();
					int ratio = ((size + plist.size()) * 100 / limit);

					if (pratio > 0) {

						int plistcount = (int) charIndexMap.getInput().get(
								"plistcount");
						int totalRecords = generatorContext.getTotalRecords();
						int cRatio = (plistcount * 100 / totalRecords);

						int nextInt = RandomUtil.nextInt(1, 100);
						if (cRatio < pratio && nextInt > cRatio
								&& RandomUtil.nextInt(0, 1) == 1 && RandomUtil.nextInt(0, 1) == 0) {
							dataTypeValue = plist.get(RandomUtil.nextInt(0,
									plist.size() - 1));
							charIndexMap.getInput().put("plistcount",
									(plistcount + 1));
						} else {
							int nextInt2 = RandomUtil.nextInt(1, 100);
							if (nextInt2 > ratio) {
								dataTypeValue = getRandomValue();
								if (!values.contains(dataTypeValue)) {
									values.add(dataTypeValue);
								}
							} else {
								int tempNext = RandomUtil.nextInt(0,
										values.size() - 1);
								dataTypeValue = values.get(tempNext);
							}
						}
					} else {
						int nextInt = RandomUtil.nextInt(1, 100);
						if (nextInt > ratio) {
							dataTypeValue = getRandomValue();
							if (!values.contains(dataTypeValue)) {
								values.add(dataTypeValue);
							}
						} else {
							int tempNext = RandomUtil.nextInt(0,
									values.size() - 1);
							dataTypeValue = values.get(tempNext);
						}
					}
				} else {
					// generate random value and add to cache
					charIndexMap = new BaseConnectorInput<String, Object>();
					// add values list
					dataTypeValue = getRandomValue();
					ArrayList<String> values = new ArrayList<>();
					values.add(dataTypeValue);
					charIndexMap.getInput().put("values", values);

					if (pratio > 0) {
						// add plistcount
						charIndexMap.getInput().put("plistcount", 0);
					}

					// update BaseConnectorInput for charIndex
					hashMap.put(charIndex, charIndexMap);
				}
			} else {
				/**
				 * generate random value and add to cache
				 */

				hashMap = new HashMap<>();
				BaseConnectorInput<String, Object> charIndexMap = new BaseConnectorInput<String, Object>();
				// add values list
				dataTypeValue = getRandomValue();
				ArrayList<String> values = new ArrayList<>();
				values.add(dataTypeValue);
				charIndexMap.getInput().put("values", values);

				if (pratio > 0) {
					// add plistcount
					charIndexMap.getInput().put("plistcount", 0);
				}

				// update BaseConnectorInput for charIndex
				hashMap.put(charIndex, charIndexMap);

				// update cache for uniqueId
				GeneratorCache.getInstance().getMap()
						.put(generatorContext.getUniqueID(), hashMap);
			}
		} else {
			// generate random value and add to cache
			dataTypeValue = getRandomValue();
		}
		// handle padding
		dataTypeValue = handlePadding(dataTypeValue);
		return dataTypeValue;
	}

	/**
	 * Handle padding.
	 *
	 * @param dataTypeValue the data type value
	 * @return the string
	 */
	private String handlePadding(String dataTypeValue) {
		String[] split = padding.split(DataTypeDefaults.VALUE_SEPERATOR);
		int paddingLength = Integer.parseInt(split[1]);
		String padChar = split[2];
		if (paddingLength > 0) {
			if (split[0].equalsIgnoreCase(Padding.LEFT.toString())) {
				dataTypeValue = StringUtils.leftPad(dataTypeValue,
						paddingLength, padChar);
			} else if (split[0].equalsIgnoreCase(Padding.RIGHT.toString())) {
				dataTypeValue = StringUtils.rightPad(dataTypeValue,
						paddingLength, padChar);
			} else if (split[0].equalsIgnoreCase(Padding.CENTER.toString())) {
				dataTypeValue = StringUtils.center(dataTypeValue,
						paddingLength, padChar);
			} else {
				System.out.println("Error : Invalid padding type...: "
						+ split[0]);
			}
		}
		return dataTypeValue;
	}

	/**
	 * The Enum Padding.
	 */
	private enum Padding {
		
		/** The right. */
		RIGHT, 
 /** The left. */
 LEFT, 
 /** The center. */
 CENTER;
	}
}
