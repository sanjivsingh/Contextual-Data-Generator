package com.sanjivsingh.datagenerator.core.datatype.impl;

import java.util.Locale;

import com.sanjivsingh.datagenerator.core.DataTypeDefaults;
import com.sanjivsingh.datagenerator.core.DataTypeParameters;
import com.sanjivsingh.datagenerator.core.datatype.BaseDataType;
import com.sanjivsingh.datagenerator.core.datatype.DataType;
import com.sanjivsingh.datagenerator.core.model.GeneratorContext;
import com.sanjivsingh.datagenerator.core.util.RandomUtil;

public class DCountryCode extends BaseDataType {

	private boolean isCapital = DataTypeDefaults.COUNTRY_IS_CAPITAL;

	private DCountryCode(int charIndex, int recordIndex,
			GeneratorContext generatorContext) {
		super(charIndex, recordIndex, generatorContext);
	}

	@Override
	public String getRandomValue() {

		String[] locales = Locale.getISOCountries();
		String countryCode = locales[RandomUtil.nextInt(0, locales.length - 1)];
		Locale obj = new Locale("", countryCode);

		String returnString = obj.getCountry();
		returnString = isCapital ? returnString.toUpperCase() : returnString
				.toLowerCase();
		return returnString;
	}

	public static DataType getInstance(Integer charIndex, Integer recordIndex,
			GeneratorContext generatorContext) {

		DCountryCode dcc = new DCountryCode(charIndex, recordIndex,
				generatorContext);
		if (dcc.getParameterMap().containsKey(
				DataTypeParameters.COUNTRY_IS_CAPITAL)) {
			dcc.setCapital(getBoolean(dcc.getParameterMap(),
					DataTypeParameters.COUNTRY_IS_CAPITAL));
		}
		return dcc;
	}

	private void setCapital(boolean isCapital) {
		this.isCapital = isCapital;
	}

}