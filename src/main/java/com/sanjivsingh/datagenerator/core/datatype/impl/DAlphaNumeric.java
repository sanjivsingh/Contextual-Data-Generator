package com.sanjivsingh.datagenerator.core.datatype.impl;

import com.sanjivsingh.datagenerator.core.DataTypeDefaults;
import com.sanjivsingh.datagenerator.core.DataTypeParameters;
import com.sanjivsingh.datagenerator.core.datatype.BaseDataType;
import com.sanjivsingh.datagenerator.core.datatype.DataType;
import com.sanjivsingh.datagenerator.core.model.GeneratorContext;
import com.sanjivsingh.datagenerator.core.util.RandomUtil;

public class DAlphaNumeric extends BaseDataType {

	private static char[] symbols;

	static {
		StringBuilder tmp = new StringBuilder();
		for (char ch = '0'; ch <= '9'; ++ch)
			tmp.append(ch);
		for (char ch = 'a'; ch <= 'z'; ++ch)
			tmp.append(ch);
		symbols = tmp.toString().toCharArray();
	}

	private int size = DataTypeDefaults.ALPHANUMERIC_SIZE;

	private void setSize(int size) {
		this.size = size;
	}

	private DAlphaNumeric(int charIndex, int recordIndex, GeneratorContext generatorContext) {
		super(charIndex, recordIndex, generatorContext);
	}

	@Override
	public String getRandomValue() {
		String number = "";
		for (int i = 0; i < size; i++) {
			number = number + symbols[RandomUtil.nextInt(0, symbols.length - 1)];
		}
		return number;
	}

	public static DataType getInstance(Integer charIndex, Integer recordIndex, GeneratorContext generatorContext) {

		DAlphaNumeric dbn = new DAlphaNumeric(charIndex, recordIndex, generatorContext);
		if (dbn.getParameterMap().containsKey(DataTypeParameters.ALPHANUMRIC_SIZE)) {
			dbn.setSize(getInt(dbn.getParameterMap(), DataTypeParameters.ALPHANUMRIC_SIZE));
		}
		return dbn;
	}

}
