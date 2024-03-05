package com.sanjivsingh.datagenerator.core.datatype.impl;

import com.sanjivsingh.datagenerator.core.DataTypeDefaults;
import com.sanjivsingh.datagenerator.core.DataTypeParameters;
import com.sanjivsingh.datagenerator.core.datatype.BaseDataType;
import com.sanjivsingh.datagenerator.core.datatype.DataType;
import com.sanjivsingh.datagenerator.core.model.GeneratorContext;
import com.sanjivsingh.datagenerator.core.util.RandomUtil;

public class DBigNumber extends BaseDataType {

	private int size = DataTypeDefaults.BIGNUMBER_SIZE;

	private void setSize(int size) {
		this.size = size;
	}

	public static DataType getInstance(Integer charIndex, Integer recordIndex,
			GeneratorContext generatorContext) {

		DBigNumber dbn = new DBigNumber(charIndex, recordIndex,
				generatorContext);
		if (dbn.getParameterMap()
				.containsKey(DataTypeParameters.BIGNUMBER_SIZE)) {
			dbn.setSize(getInt(dbn.getParameterMap(),
					DataTypeParameters.BIGNUMBER_SIZE));
		}
		return dbn;
	}

	private DBigNumber(int charIndex, int recordIndex,
			GeneratorContext generatorContext) {
		super(charIndex, recordIndex, generatorContext);
	}

	@Override
	public String getRandomValue() {
		String number = "";
		for (int i = 0; i < size; i++) {
			number = number + RandomUtil.nextInt(0, 9);
		}
		return number;
	}

}
