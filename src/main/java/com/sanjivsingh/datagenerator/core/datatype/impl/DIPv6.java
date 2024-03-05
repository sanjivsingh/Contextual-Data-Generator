package com.sanjivsingh.datagenerator.core.datatype.impl;

import java.util.Random;

import com.googlecode.ipv6.IPv6Address;
import com.sanjivsingh.datagenerator.core.datatype.BaseDataType;
import com.sanjivsingh.datagenerator.core.datatype.DataType;
import com.sanjivsingh.datagenerator.core.model.GeneratorContext;

public class DIPv6 extends BaseDataType {

	@Override
	public String getRandomValue() {
		Random r = new Random();
		IPv6Address fromLongs = IPv6Address.fromLongs(r.nextLong(),
				r.nextLong());
		return fromLongs.toString();
	}

	public static DataType getInstance(Integer charIndex, Integer recordIndex,
			GeneratorContext generatorContext) {

		DIPv6 dIPv6 = new DIPv6(charIndex, recordIndex, generatorContext);
		return dIPv6;
	}

	private DIPv6(int charIndex, int recordIndex,
			GeneratorContext generatorContext) {
		super(charIndex, recordIndex, generatorContext);
		// TODO Auto-generated constructor stub
	}

}
