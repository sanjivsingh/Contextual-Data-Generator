package com.sanjivsingh.datagenerator.core.datatype.impl;

import java.util.TimeZone;

import com.sanjivsingh.datagenerator.core.datatype.BaseDataType;
import com.sanjivsingh.datagenerator.core.datatype.DataType;
import com.sanjivsingh.datagenerator.core.model.GeneratorContext;
import com.sanjivsingh.datagenerator.core.util.RandomUtil;

public class DTimeZone extends BaseDataType {

	@Override
	public String getRandomValue() {
		String[] availableIDs = TimeZone.getAvailableIDs();
		return TimeZone.getTimeZone(
				availableIDs[RandomUtil.nextInt(0, availableIDs.length - 1)])
				.getID();
	}

	public static DataType getInstance(Integer charIndex, Integer recordIndex,
			GeneratorContext generatorContext) {
		DTimeZone dtz = new DTimeZone(charIndex, recordIndex, generatorContext);
		return dtz;
	}

	private DTimeZone(int charIndex, int recordIndex,
			GeneratorContext generatorContext) {
		super(charIndex, recordIndex, generatorContext);
	}

}
