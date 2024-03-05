package com.sanjivsingh.datagenerator.core.datatype.impl;

import com.sanjivsingh.datagenerator.core.datatype.BaseDataType;
import com.sanjivsingh.datagenerator.core.datatype.DataType;
import com.sanjivsingh.datagenerator.core.model.GeneratorContext;
import com.sanjivsingh.datagenerator.core.util.DomainUtil;

public class DEmailAddress extends BaseDataType {

	private DEmailAddress(int charIndex, int recordIndex,
			GeneratorContext generatorContext) {
		super(charIndex, recordIndex, generatorContext);
	}

	@Override
	public String getRandomValue() {
		return DomainUtil.getEmailAddress();
	}

	public static DataType getInstance(Integer charIndex, Integer recordIndex,
			GeneratorContext generatorContext) {
		DEmailAddress dEmailAdress = new DEmailAddress(charIndex, recordIndex,
				generatorContext);
		return dEmailAdress;
	}

}
