package com.sanjivsingh.datagenerator.core.datatype.impl;

import java.text.DecimalFormat;

import com.sanjivsingh.datagenerator.core.DataTypeDefaults;
import com.sanjivsingh.datagenerator.core.DataTypeParameters;
import com.sanjivsingh.datagenerator.core.datatype.BaseDataType;
import com.sanjivsingh.datagenerator.core.datatype.DataType;
import com.sanjivsingh.datagenerator.core.model.GeneratorContext;

public class DLatitude extends BaseDataType {

	private String format = DataTypeDefaults.LATITUDE_FORMAT;

	private void setFormat(String format) {
		this.format = format;
	}

	private DLatitude(int charIndex, int recordIndex,
			GeneratorContext generatorContext) {
		super(charIndex, recordIndex, generatorContext);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String getRandomValue() {
		double minLat = -90.00;
		double maxLat = 90.00;
		double latitude = minLat
				+ (double) (Math.random() * ((maxLat - minLat) + 1));

		DecimalFormat df = new DecimalFormat(format);
		return df.format(latitude);
	}

	public static DataType getInstance(Integer charIndex, Integer recordIndex,
			GeneratorContext generatorContext) {

		DLatitude dLatitude = new DLatitude(charIndex, recordIndex,
				generatorContext);
		if (dLatitude.getParameterMap().containsKey(
				DataTypeParameters.LATITUDE_FORMAT)) {
			dLatitude.setFormat((String) dLatitude.getParameterMap().get(
					DataTypeParameters.LATITUDE_FORMAT));
		}
		return dLatitude;
	}

}
