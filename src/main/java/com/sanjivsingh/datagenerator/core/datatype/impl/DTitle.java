package com.sanjivsingh.datagenerator.core.datatype.impl;

import com.sanjivsingh.datagenerator.core.datatype.BaseDataType;
import com.sanjivsingh.datagenerator.core.datatype.DataType;
import com.sanjivsingh.datagenerator.core.model.GeneratorContext;
import com.sanjivsingh.datagenerator.core.util.RandomUtil;

public class DTitle extends BaseDataType {

	private String[] titles = { "Honorable", "Dr", "Ms", "Mr", "Rev", "Mrs" };

	@Override
	public String getRandomValue() {
		return titles[RandomUtil.nextInt(0, titles.length - 1)];
	}

	public static DataType getInstance(Integer charIndex, Integer recordIndex,
			GeneratorContext generatorContext) {
		DTitle dcc = new DTitle(charIndex, recordIndex, generatorContext);
		return dcc;
	}

	private DTitle(int charIndex, int recordIndex,
			GeneratorContext generatorContext) {
		super(charIndex, recordIndex, generatorContext);
	}

}
