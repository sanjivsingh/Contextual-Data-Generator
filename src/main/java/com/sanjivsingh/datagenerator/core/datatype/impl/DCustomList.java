package com.sanjivsingh.datagenerator.core.datatype.impl;

import com.sanjivsingh.datagenerator.core.DataTypeDefaults;
import com.sanjivsingh.datagenerator.core.DataTypeParameters;
import com.sanjivsingh.datagenerator.core.datatype.BaseDataType;
import com.sanjivsingh.datagenerator.core.datatype.DataType;
import com.sanjivsingh.datagenerator.core.model.GeneratorContext;
import com.sanjivsingh.datagenerator.core.util.RandomUtil;

public class DCustomList extends BaseDataType {

	private String values;

	public static DataType getInstance(Integer charIndex, Integer recordIndex,
			GeneratorContext generatorContext) {

		DCustomList dbn = new DCustomList(charIndex, recordIndex,
				generatorContext);
		if (dbn.getParameterMap().containsKey(
				DataTypeParameters.CUSTOMLIST_VALUES)) {
			dbn.setValues((String) dbn.getParameterMap().get(
					DataTypeParameters.CUSTOMLIST_VALUES));
		}
		return dbn;
	}

	private DCustomList(int charIndex, int recordIndex,
			GeneratorContext generatorContext) {
		super(charIndex, recordIndex, generatorContext);
	}

	@Override
	public String getRandomValue() {
		String[] split = values.split(DataTypeDefaults.VALUE_SEPERATOR);
		int nextInt = RandomUtil.nextInt(0, split.length - 1);
		return split[nextInt];
	}

	private void setValues(String values) {
		this.values = values;
	}

}
