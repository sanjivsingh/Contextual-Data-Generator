package com.sanjivsingh.datagenerator.core.datatype.impl;

import com.sanjivsingh.datagenerator.core.DataTypeDefaults;
import com.sanjivsingh.datagenerator.core.DataTypeParameters;
import com.sanjivsingh.datagenerator.core.datatype.BaseDataType;
import com.sanjivsingh.datagenerator.core.datatype.DataType;
import com.sanjivsingh.datagenerator.core.model.GeneratorContext;
import com.sanjivsingh.datagenerator.core.util.RandomUtil;

/**
 * The Class DBoolean.
 */
public class DBoolean extends BaseDataType {

	/** The abbrev. */
	private boolean abbrev = DataTypeDefaults.BOOLEAN_ABBREV;
	
	/** The uppar. */
	private boolean uppar = DataTypeDefaults.BOOLEAN_UPPAR;

	/**
	 * Gets the single instance of DBoolean.
	 *
	 * @param charIndex the char index
	 * @param recordIndex the record index
	 * @param generatorContext the generator context
	 * @return single instance of DBoolean
	 */
	public static DataType getInstance(Integer charIndex, Integer recordIndex,
			GeneratorContext generatorContext) {

		DBoolean dbn = new DBoolean(charIndex, recordIndex, generatorContext);
		if (dbn.getParameterMap()
				.containsKey(DataTypeParameters.BOOLEAN_ABBREV)) {
			dbn.setAbbrev(getBoolean(dbn.getParameterMap(),
					DataTypeParameters.BOOLEAN_ABBREV));
		}
		if (dbn.getParameterMap().containsKey(DataTypeParameters.BOOLEAN_UPPAR)) {
			dbn.setUppar(getBoolean(dbn.getParameterMap(),
					DataTypeParameters.BOOLEAN_UPPAR));
		}
		return dbn;
	}

	/**
	 * Sets the abbrev.
	 *
	 * @param abbrev the new abbrev
	 */
	private void setAbbrev(boolean abbrev) {
		this.abbrev = abbrev;
	}

	/**
	 * Sets the uppar.
	 *
	 * @param uppar the new uppar
	 */
	private void setUppar(boolean uppar) {
		this.uppar = uppar;
	}

	/**
	 * Instantiates a new d boolean.
	 *
	 * @param charIndex the char index
	 * @param recordIndex the record index
	 * @param generatorContext the generator context
	 */
	private DBoolean(int charIndex, int recordIndex,
			GeneratorContext generatorContext) {
		super(charIndex, recordIndex, generatorContext);
	}

	/**
	 * Gets the random value.
	 *
	 * @return the random value
	 */
	@Override
	public String getRandomValue() {

		String returnStr = (RandomUtil.nextInt(1, 2) == 1) ? Boolean.TRUE
				.toString().toLowerCase() : Boolean.FALSE.toString()
				.toLowerCase();
		returnStr = abbrev ? returnStr.substring(0, 1) : returnStr;
		returnStr = uppar ? returnStr.toUpperCase() : returnStr;
		return returnStr;
	}

}
