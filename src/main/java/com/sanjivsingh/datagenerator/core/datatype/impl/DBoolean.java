package com.sanjivsingh.datagenerator.core.datatype.impl;

import com.sanjivsingh.datagenerator.core.DataTypeDefaults;
import com.sanjivsingh.datagenerator.core.DataTypeParameters;
import com.sanjivsingh.datagenerator.core.datatype.BaseDataType;
import com.sanjivsingh.datagenerator.core.datatype.DataType;
import com.sanjivsingh.datagenerator.core.model.GeneratorContext;
import com.sanjivsingh.datagenerator.core.util.RandomUtil;

public class DBoolean extends BaseDataType {

	private boolean abbrev = DataTypeDefaults.BOOLEAN_ABBREV;
	private boolean uppar = DataTypeDefaults.BOOLEAN_UPPAR;

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

	private void setAbbrev(boolean abbrev) {
		this.abbrev = abbrev;
	}

	private void setUppar(boolean uppar) {
		this.uppar = uppar;
	}

	private DBoolean(int charIndex, int recordIndex,
			GeneratorContext generatorContext) {
		super(charIndex, recordIndex, generatorContext);
	}

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
