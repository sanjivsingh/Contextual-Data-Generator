package com.sanjivsingh.datagenerator.core.datatype.impl;

import com.sanjivsingh.datagenerator.core.datatype.BaseDataType;
import com.sanjivsingh.datagenerator.core.datatype.DataType;
import com.sanjivsingh.datagenerator.core.model.GeneratorContext;
import com.sanjivsingh.datagenerator.core.util.RandomUtil;

public class DCreditCardType extends BaseDataType {

	private String[] types = { "American Express", "Diners Club",
			"Discover Card", "Diners Club enRoute",
			"Diners Club International", "JCB", "JCB 15", "MasterCard", "Visa",
			"Voyager", "Diners Club Carte Blanche", "Dankort", "Solo",
			"Switch", "Visa Electron", "InstaPayment", "China UnionPay" };

	@Override
	public String getRandomValue() {
		return types[RandomUtil.nextInt(0, types.length - 1)];
	}

	public static DataType getInstance(Integer charIndex, Integer recordIndex,
			GeneratorContext generatorContext) {
		DCreditCardType dCreditCardType = new DCreditCardType(charIndex,
				recordIndex, generatorContext);
		return dCreditCardType;
	}

	private DCreditCardType(int charIndex, int recordIndex,
			GeneratorContext generatorContext) {
		super(charIndex, recordIndex, generatorContext);
	}

}
