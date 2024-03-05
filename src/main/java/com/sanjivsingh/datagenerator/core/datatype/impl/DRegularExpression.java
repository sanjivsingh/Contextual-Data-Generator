package com.sanjivsingh.datagenerator.core.datatype.impl;

import nl.flotsam.xeger.Xeger;

import com.sanjivsingh.datagenerator.core.DataTypeParameters;
import com.sanjivsingh.datagenerator.core.datatype.BaseDataType;
import com.sanjivsingh.datagenerator.core.datatype.DataType;
import com.sanjivsingh.datagenerator.core.model.GeneratorContext;

public class DRegularExpression extends BaseDataType {

	private String regex;

	private DRegularExpression(int charIndex, Integer recordIndex,
			GeneratorContext generatorContext) {
		super(charIndex, recordIndex, generatorContext);
	}

	private void setRegex(String regex) {
		this.regex = regex;
	}

	@Override
	public String getRandomValue() {
		Xeger generator = new Xeger(regex);
		return generator.generate();
	}

	public static DataType getInstance(Integer charIndex, Integer recordIndex,
			GeneratorContext generatorContext) {

		DRegularExpression dre = new DRegularExpression(charIndex, recordIndex,
				generatorContext);
		if (dre.getParameterMap().containsKey(
				DataTypeParameters.REGULAR_EXPRESSION_REGEX)) {
			dre.setRegex((String) dre.getParameterMap().get(
					DataTypeParameters.REGULAR_EXPRESSION_REGEX));
		}
		return dre;
	}

}
