package com.sanjivsingh.datagenerator.core.datatype;

import java.lang.reflect.Method;
import java.util.Map;

import com.sanjivsingh.datagenerator.common.PropertiesManager;
import com.sanjivsingh.datagenerator.core.DataTypeParameters;
import com.sanjivsingh.datagenerator.core.model.GeneratorContext;
import com.sanjivsingh.datagenerator.core.util.DataUtil;
import com.sanjivsingh.datagenerator.exception.DataGenException;

/**
 * The Class DataTypeBuilder.
 *
 * @author Sanjiv.Singh
 */
public class DataTypeBuilder {

	/**
	 * Gets the data type.
	 *
	 * @param charIndex the char index
	 * @param recordIndex the record index
	 * @param generatorContext the generator context
	 * @return the data type
	 */
	public DataType getDataType(int charIndex, int recordIndex,
			GeneratorContext generatorContext) {

		DataType dataType = null;
		if (DataTypeValidator.validate(charIndex, generatorContext)) {
			dataType = prepareDataType(charIndex, recordIndex, generatorContext);
			if (dataType == null) {
				throw new DataGenException(
						"No handler avalible for data type Expression : "
								+ generatorContext.getExpression(charIndex));
			}
		} else {
			throw new DataGenException("'" + DataTypeParameters.NAME_FIELD
					+ "' field is missing in dataType expression : "
					+ generatorContext.getExpression(charIndex));
		}
		return dataType;
	}

	/**
	 * Prepare data type.
	 *
	 * @param charIndex the char index
	 * @param recordIndex the record index
	 * @param generatorContext the generator context
	 * @return the data type
	 */
	private DataType prepareDataType(int charIndex, int recordIndex,
			GeneratorContext generatorContext) {
		String dataTypeExpression = generatorContext.getExpression(charIndex);
		Map<String, Object> parameterMap = DataUtil.getMap(dataTypeExpression);
		try {
			String dataTypeClass = PropertiesManager
					.getDataTypeClassesProperties().getValue(
							(String) parameterMap
									.get(DataTypeParameters.NAME_FIELD)
									+ ".CLASS");
			if (null == dataTypeClass || dataTypeClass.equals("")) {
				throw new DataGenException(
						"Mapping DataType => JavaClass missing in "
								+ PropertiesManager.getSystemProperties()
										.getValue("datatype.classes.file.path")
								+ " for expressioin : " + dataTypeExpression);
			}
			Class<?> forName = Class.forName(dataTypeClass);
			Class[] argTypes = { Integer.class, Integer.class,
					GeneratorContext.class };
			Method method = forName.getMethod("getInstance", argTypes);
			Object[] arguments = { charIndex, recordIndex, generatorContext };
			return (DataType) method.invoke(null, arguments);
		} catch (Exception ex) {
			throw new DataGenException(
					"Error while parsing dataType Expression : "
							+ dataTypeExpression);
		}
	}

}
