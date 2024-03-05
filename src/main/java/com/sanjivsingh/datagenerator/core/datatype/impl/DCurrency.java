package com.sanjivsingh.datagenerator.core.datatype.impl;

import java.util.Currency;
import java.util.Locale;

import com.sanjivsingh.datagenerator.core.datatype.BaseDataType;
import com.sanjivsingh.datagenerator.core.datatype.DataType;
import com.sanjivsingh.datagenerator.core.model.GeneratorContext;
import com.sanjivsingh.datagenerator.core.util.RandomUtil;

public class DCurrency extends BaseDataType {

	private DCurrency(int charIndex, int recordIndex,
			GeneratorContext generatorContext) {
		super(charIndex, recordIndex, generatorContext);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String getRandomValue() {
		Locale[] locales = Locale.getAvailableLocales();
		boolean found = false;
		Currency instance = null;
		while (!found) {
			try {
				Locale locale = locales[RandomUtil.nextInt(0,
						locales.length - 1)];
				instance = Currency.getInstance(locale);
				found = true;
			} catch (Exception e) {
				// when the locale is not supported
			}
		}

		return instance.getDisplayName();
	}

	public static DataType getInstance(Integer charIndex, Integer recordIndex,
			GeneratorContext generatorContext) {
		DCurrency dc = new DCurrency(charIndex, recordIndex, generatorContext);
		return dc;
	}

}
