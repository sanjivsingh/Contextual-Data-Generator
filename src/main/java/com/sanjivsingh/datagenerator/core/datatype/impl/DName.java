package com.sanjivsingh.datagenerator.core.datatype.impl;

import com.sanjivsingh.datagenerator.core.datatype.BaseDataType;
import com.sanjivsingh.datagenerator.core.datatype.DataType;
import com.sanjivsingh.datagenerator.core.model.GeneratorContext;
import com.sanjivsingh.datagenerator.core.util.RandomUtil;

public class DName extends BaseDataType {

	public DName(int charIndex, int recordIndex,
			GeneratorContext generatorContext) {
		super(charIndex, recordIndex, generatorContext);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String getRandomValue() {
		String[] names = { "sanjiv", "rajiv", "pankaj", "neeraj", "dheeraj",
				"ajeet", "gaurav", };
		int nextInt = RandomUtil.nextInt(0, names.length - 1);
		return names[nextInt];
	}

	public static DataType getInstance(Integer charIndex, Integer recordIndex,
			GeneratorContext generatorContext) {

		// TODO Auto-generated method stub
		return null;
	}

}
