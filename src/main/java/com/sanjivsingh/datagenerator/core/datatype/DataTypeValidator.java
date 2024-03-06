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
package com.sanjivsingh.datagenerator.core.datatype;

import java.util.Map;

import com.sanjivsingh.datagenerator.core.DataTypeParameters;
import com.sanjivsingh.datagenerator.core.model.ExpressssionValue;
import com.sanjivsingh.datagenerator.core.model.GeneratorContext;
import com.sanjivsingh.datagenerator.core.util.DataUtil;

/**
 * The Class DataTypeValidator.
 *
 * @author Sanjiv.Singh
 */
public class DataTypeValidator {

	/**
	 * Validate.
	 *
	 * @param dataTypeExpression the data type expression
	 * @return true, if successful
	 */
	public static boolean validate(String dataTypeExpression) {
		Map<String, Object> parameterMap = DataUtil.getMap(dataTypeExpression);
		if (parameterMap.containsKey(DataTypeParameters.NAME_FIELD)) {
			for (DataTypes dType : DataTypes.values()) {
				if (dType.getLabel().equals(parameterMap.get(DataTypeParameters.NAME_FIELD))) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * Validate.
	 *
	 * @param charIndex        the char index
	 * @param generatorContext the generator context
	 * @return true, if successful
	 */
	public static boolean validate(int charIndex, GeneratorContext generatorContext) {
		if (validate(generatorContext.getExpression(charIndex))) {
			return true;
		} else {
			String id = (String) DataUtil.getMap(generatorContext.getExpression(charIndex))
					.get(DataTypeParameters.ID_FIELD);

			if (null != id) {
				for (ExpressssionValue eValue : generatorContext.getExpresssionById(id)) {
					if (validate(eValue.getExpression())) {
						return true;
					}

				}
			}
		}
		return false;
	}
}
