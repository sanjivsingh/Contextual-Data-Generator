/*
 * Copyright 2024 Sanjiv Singh
 * 
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * 
 */
package com.sanjivsingh.datagenerator.core.datatype.impl;

import java.util.Locale;

import com.sanjivsingh.datagenerator.core.DataTypeDefaults;
import com.sanjivsingh.datagenerator.core.DataTypeParameters;
import com.sanjivsingh.datagenerator.core.datatype.BaseDataType;
import com.sanjivsingh.datagenerator.core.datatype.DataType;
import com.sanjivsingh.datagenerator.core.model.GeneratorContext;
import com.sanjivsingh.datagenerator.core.util.RandomUtil;

/**
 * The Class DCountry.
 */
public class DCountry extends BaseDataType {

	/** The is capital. */
	private boolean isCapital = DataTypeDefaults.COUNTRY_IS_CAPITAL;
	
	/** The is code. */
	private boolean isCode = DataTypeDefaults.COUNTRY_IS_CODE;

	/**
	 * Gets the random value.
	 *
	 * @return the random value
	 */
	@Override
	public String getRandomValue() {

		String[] locales = Locale.getISOCountries();
		String countryCode = locales[RandomUtil.nextInt(0, locales.length - 1)];
		Locale obj = new Locale("", countryCode);

		String returnString = (isCode) ? obj.getCountry() : obj
				.getDisplayCountry();
		returnString = isCapital ? returnString.toUpperCase() : returnString;
		return returnString;
	}

	/**
	 * Gets the single instance of DCountry.
	 *
	 * @param charIndex the char index
	 * @param recordIndex the record index
	 * @param generatorContext the generator context
	 * @return single instance of DCountry
	 */
	public static DataType getInstance(Integer charIndex, Integer recordIndex,
			GeneratorContext generatorContext) {

		DCountry dcc = new DCountry(charIndex, recordIndex, generatorContext);
		if (dcc.getParameterMap().containsKey(
				DataTypeParameters.COUNTRY_IS_CAPITAL)) {
			dcc.setCapital(getBoolean(dcc.getParameterMap(),
					DataTypeParameters.COUNTRY_IS_CAPITAL));
		}
		if (dcc.getParameterMap().containsKey(
				DataTypeParameters.COUNTRY_IS_CODE)) {
			dcc.setCode(getBoolean(dcc.getParameterMap(),
					DataTypeParameters.COUNTRY_IS_CODE));
		}
		return dcc;
	}

	/**
	 * Instantiates a new d country.
	 *
	 * @param charIndex the char index
	 * @param recordIndex the record index
	 * @param generatorContext the generator context
	 */
	private DCountry(int charIndex, int recordIndex,
			GeneratorContext generatorContext) {
		super(charIndex, recordIndex, generatorContext);
	}

	/**
	 * Sets the capital.
	 *
	 * @param isCapital the new capital
	 */
	private void setCapital(boolean isCapital) {
		this.isCapital = isCapital;
	}

	/**
	 * Sets the code.
	 *
	 * @param isCode the new code
	 */
	private void setCode(boolean isCode) {
		this.isCode = isCode;
	}

}
