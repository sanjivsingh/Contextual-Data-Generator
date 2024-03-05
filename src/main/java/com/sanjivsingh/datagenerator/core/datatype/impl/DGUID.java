package com.sanjivsingh.datagenerator.core.datatype.impl;

import java.util.UUID;

import com.sanjivsingh.datagenerator.core.datatype.BaseDataType;
import com.sanjivsingh.datagenerator.core.datatype.DataType;
import com.sanjivsingh.datagenerator.core.model.GeneratorContext;

public class DGUID extends BaseDataType {

	@Override
	public String getRandomValue() {
		UUID randomUUID = UUID.randomUUID();
		return randomUUID.toString();
	}

	public static DataType getInstance(Integer charIndex, Integer recordIndex,
			GeneratorContext generatorContext) {
		DGUID dGUID = new DGUID(charIndex, recordIndex, generatorContext);
		return dGUID;
	}

	private DGUID(int charIndex, int recordIndex,
			GeneratorContext generatorContext) {
		super(charIndex, recordIndex, generatorContext);
		// TODO Auto-generated constructor stub
	}

}
