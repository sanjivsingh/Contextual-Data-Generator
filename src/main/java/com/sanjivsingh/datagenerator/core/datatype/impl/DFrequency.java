package com.sanjivsingh.datagenerator.core.datatype.impl;

import com.sanjivsingh.datagenerator.core.datatype.BaseDataType;
import com.sanjivsingh.datagenerator.core.datatype.DataType;
import com.sanjivsingh.datagenerator.core.model.GeneratorContext;
import com.sanjivsingh.datagenerator.core.util.RandomUtil;

public class DFrequency extends BaseDataType {

	private String[] frequencies = { "Daily", "Often", "Once", "Weekly",
			"Seldom", "Monthly", "Yearly", "Never" };

	@Override
	public String getRandomValue() {
		return frequencies[RandomUtil.nextInt(0, frequencies.length - 1)];
	}

	public static DataType getInstance(Integer charIndex, Integer recordIndex,
			GeneratorContext generatorContext) {
		DFrequency dFrequency = new DFrequency(charIndex, recordIndex,
				generatorContext);
		return dFrequency;
	}

	private DFrequency(int charIndex, int recordIndex,
			GeneratorContext generatorContext) {
		super(charIndex, recordIndex, generatorContext);
		// TODO Auto-generated constructor stub
	}

}
