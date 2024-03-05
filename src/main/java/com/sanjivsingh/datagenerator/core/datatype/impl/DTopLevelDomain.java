package com.sanjivsingh.datagenerator.core.datatype.impl;

import com.sanjivsingh.datagenerator.core.datatype.BaseDataType;
import com.sanjivsingh.datagenerator.core.datatype.DataType;
import com.sanjivsingh.datagenerator.core.model.GeneratorContext;
import com.sanjivsingh.datagenerator.core.util.DomainUtil;

public class DTopLevelDomain extends BaseDataType {

	private DTopLevelDomain(int charIndex, int recordIndex,
			GeneratorContext generatorContext) {
		super(charIndex, recordIndex, generatorContext);
	}

	@Override
	public String getRandomValue() {
		return DomainUtil.getTopDomain();
	}

	public static DataType getInstance(Integer charIndex, Integer recordIndex,
			GeneratorContext generatorContext) {

		DTopLevelDomain dTopLevelDomain = new DTopLevelDomain(charIndex,
				recordIndex, generatorContext);
		return dTopLevelDomain;
	}

}
