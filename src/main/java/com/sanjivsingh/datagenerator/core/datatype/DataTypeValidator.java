package com.sanjivsingh.datagenerator.core.datatype;

import java.util.Map;

import com.sanjivsingh.datagenerator.core.DataTypeParameters;
import com.sanjivsingh.datagenerator.core.model.ExpressssionValue;
import com.sanjivsingh.datagenerator.core.model.GeneratorContext;
import com.sanjivsingh.datagenerator.core.util.DataUtil;


/**
 * @author Sanjiv.Singh
 * 
 */
public class DataTypeValidator {

	public static boolean validate(String dataTypeExpression) {
		Map<String, Object> parameterMap = DataUtil.getMap(dataTypeExpression);
		if (parameterMap.containsKey(DataTypeParameters.NAME_FIELD)) {
			for (DataTypes dType : DataTypes.values()) {
				if (dType.getLabel().equals(
						parameterMap.get(DataTypeParameters.NAME_FIELD))) {
					return true;
				}
			}
		}
		return false;
	}

	public static boolean validate(int charIndex,
			GeneratorContext generatorContext) {
		if (validate(generatorContext.getExpression(charIndex))) {
			return true;
		} else {
			String id = (String) DataUtil.getMap(
					generatorContext.getExpression(charIndex)).get(
					DataTypeParameters.ID_FIELD);

			if (null != id) {
				for (ExpressssionValue eValue : generatorContext
						.getExpresssionById(id)) {
					if (validate(eValue.getExpression())) {
						return true;
					}

				}
			}
		}
		return false;
	}
}
