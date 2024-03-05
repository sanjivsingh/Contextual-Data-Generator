package com.sanjivsingh.datagenerator.core.datatype.impl;

import com.sanjivsingh.datagenerator.core.DataTypeDefaults;
import com.sanjivsingh.datagenerator.core.DataTypeParameters;
import com.sanjivsingh.datagenerator.core.datatype.BaseDataType;
import com.sanjivsingh.datagenerator.core.datatype.DataType;
import com.sanjivsingh.datagenerator.core.model.GeneratorContext;
import com.sanjivsingh.datagenerator.core.util.RandomUtil;

public class DGender extends BaseDataType {

	private boolean abbrev = DataTypeDefaults.GENDER_ABBREV;
	private boolean uppar = DataTypeDefaults.GENDER_UPPAR;

	private enum Value {
		Male, Female;
	}

	private void setAbbrev(boolean abbrev) {
		this.abbrev = abbrev;
	}

	private void setUppar(boolean uppar) {
		this.uppar = uppar;
	}

	private DGender(int charIndex, Integer recordIndex,
			GeneratorContext generatorContext) {
		super(charIndex, recordIndex, generatorContext);
	}

	@Override
	public String getRandomValue() {

		String returnStr = (RandomUtil.nextInt(1, 2) == 1) ? Value.Male
				.toString() : Value.Female.toString();
		returnStr = abbrev ? returnStr.substring(0, 1) : returnStr;
		returnStr = uppar ? returnStr.toUpperCase() : returnStr;
		return returnStr;
	}

	public static DataType getInstance(Integer charIndex, Integer recordIndex,
			GeneratorContext generatorContext) {

		DGender dGender = new DGender(charIndex, recordIndex, generatorContext);
		if (dGender.getParameterMap().containsKey(
				DataTypeParameters.GENDER_ABBREV)) {
			dGender.setAbbrev(getBoolean(dGender.getParameterMap(),
					DataTypeParameters.GENDER_ABBREV));
		}
		if (dGender.getParameterMap().containsKey(
				DataTypeParameters.GENDER_UPPAR)) {
			dGender.setUppar(getBoolean(dGender.getParameterMap(),
					DataTypeParameters.GENDER_UPPAR));
		}
		return dGender;
	}

}
