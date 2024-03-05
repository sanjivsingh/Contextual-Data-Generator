package com.sanjivsingh.datagenerator.core.datatype.impl;

import java.util.Random;

import com.sanjivsingh.datagenerator.core.DataTypeDefaults;
import com.sanjivsingh.datagenerator.core.DataTypeParameters;
import com.sanjivsingh.datagenerator.core.datatype.BaseDataType;
import com.sanjivsingh.datagenerator.core.datatype.DataType;
import com.sanjivsingh.datagenerator.core.model.GeneratorContext;
import com.sanjivsingh.datagenerator.core.util.IpConverter;

public class DIPv4 extends BaseDataType {

	private String format = DataTypeDefaults.IP_FORMAT;

	private void setFormat(String format) {
		this.format = format;
	}

	private DIPv4(int charIndex, int recordIndex,
			GeneratorContext generatorContext) {
		super(charIndex, recordIndex, generatorContext);
	}

	@Override
	public String getRandomValue() {
		return getIpFormat(format);
	}

	private String getIpFormat(String format2) {

		Random r = new Random();
		String longToIp = IpConverter.longToIp(r.nextLong() | 0x00000000L);

		String[] split = format2.split(DataTypeDefaults.IP_SEPERATOR);
		String[] split2 = longToIp.split(DataTypeDefaults.IP_SEPERATOR);
		for (int i = 0; i < split.length; i++) {
			if (!split[i].equals("*")) {
				split2[i] = split[i];
			}
		}

		String returnString = "";
		for (int i = 0; i < split2.length; i++) {
			returnString = returnString + split2[i] + ".";
		}
		return returnString.substring(0, returnString.length() - 2);
	}

	public static DataType getInstance(Integer charIndex, Integer recordIndex,
			GeneratorContext generatorContext) {

		DIPv4 dIPv4 = new DIPv4(charIndex, recordIndex, generatorContext);
		if (dIPv4.getParameterMap().containsKey(DataTypeParameters.IP_FORMAT)) {
			dIPv4.setFormat((String) dIPv4.getParameterMap().get(
					DataTypeParameters.IP_FORMAT));
		}
		return dIPv4;
	}

}
