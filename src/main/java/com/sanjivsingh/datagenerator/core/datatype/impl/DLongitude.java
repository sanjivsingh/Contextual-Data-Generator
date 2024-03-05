package com.sanjivsingh.datagenerator.core.datatype.impl;

import java.text.DecimalFormat;

import com.sanjivsingh.datagenerator.core.DataTypeDefaults;
import com.sanjivsingh.datagenerator.core.DataTypeParameters;
import com.sanjivsingh.datagenerator.core.datatype.BaseDataType;
import com.sanjivsingh.datagenerator.core.datatype.DataType;
import com.sanjivsingh.datagenerator.core.model.GeneratorContext;

public class DLongitude extends BaseDataType {

	private String format = DataTypeDefaults.LONGITUDE_FORMAT;

	private void setFormat(String format) {
		this.format = format;
	}

	private DLongitude(int charIndex, int recordIndex,
			GeneratorContext generatorContext) {
		super(charIndex, recordIndex, generatorContext);
	}

	@Override
	public String getRandomValue() {
		double minLon = -180.00;
		double maxLon = 180.00;
		double longitude = minLon
				+ (double) (Math.random() * ((maxLon - minLon) + 1));
		DecimalFormat df = new DecimalFormat(format);
		return df.format(longitude);
	}

	public static DataType getInstance(Integer charIndex, Integer recordIndex,
			GeneratorContext generatorContext) {

		DLongitude dLongitude = new DLongitude(charIndex, recordIndex,
				generatorContext);
		if (dLongitude.getParameterMap().containsKey(
				DataTypeParameters.LONGITUDE_FORMAT)) {
			dLongitude.setFormat((String) dLongitude.getParameterMap().get(
					DataTypeParameters.LONGITUDE_FORMAT));
		}
		return dLongitude;
	}

}