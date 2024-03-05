package com.sanjivsingh.datagenerator.core.datatype.impl;

import java.text.DecimalFormat;

import com.sanjivsingh.datagenerator.core.DataTypeDefaults;
import com.sanjivsingh.datagenerator.core.DataTypeParameters;
import com.sanjivsingh.datagenerator.core.datatype.BaseDataType;
import com.sanjivsingh.datagenerator.core.datatype.DataType;
import com.sanjivsingh.datagenerator.core.model.GeneratorContext;

public class DRowNumber extends BaseDataType {

	private int decimal = DataTypeDefaults.ROWNUMBER_DECIMAL;
	private String start = DataTypeDefaults.ROWNUMBE_START;
	private String end = DataTypeDefaults.ROWNUMBE_END;

	private DRowNumber(int charIndex, int recordIndex,
			GeneratorContext generatorContext) {
		super(charIndex, recordIndex, generatorContext);
	}

	@Override
	public String getRandomValue() {

		String format = getFormat(decimal);

		double minLon = Double.parseDouble(start);
		double maxLon = Double.parseDouble(end);
		double number = minLon
				+ (double) (Math.random() * ((maxLon - minLon) + 1));
		DecimalFormat df = new DecimalFormat(format);
		return df.format(number);
	}

	private String getFormat(int decimal) {
		String returnStr = "#";
		if (decimal > 0) {
			returnStr = returnStr + ".";
			for (int i = 1; i <= decimal; i++) {
				returnStr = returnStr + "0";
			}
		}
		return returnStr;
	}

	public static DataType getInstance(Integer charIndex, Integer recordIndex,
			GeneratorContext generatorContext) {

		DRowNumber drn = new DRowNumber(charIndex, recordIndex,
				generatorContext);
		if (drn.getParameterMap().containsKey(
				DataTypeParameters.ROWNUMBER_DECIMAL)) {
			drn.setDecimal(getInt(drn.getParameterMap(),
					DataTypeParameters.ROWNUMBER_DECIMAL));
		}
		if (drn.getParameterMap()
				.containsKey(DataTypeParameters.ROWNUMBE_START)) {
			drn.setStart((String) drn.getParameterMap().get(
					DataTypeParameters.ROWNUMBE_START));
		}
		if (drn.getParameterMap().containsKey(DataTypeParameters.ROWNUMBE_END)) {
			drn.setEnd((String) drn.getParameterMap().get(
					DataTypeParameters.ROWNUMBE_END));
		}
		return drn;

	}

	private void setDecimal(int decimal) {
		this.decimal = decimal;
	}

	private void setStart(String start) {
		this.start = start;
	}

	private void setEnd(String end) {
		this.end = end;
	}

}