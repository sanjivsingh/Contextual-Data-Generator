package com.sanjivsingh.datagenerator.core.datatype.impl;

import java.util.Random;

import com.sanjivsingh.datagenerator.core.DataTypeDefaults;
import com.sanjivsingh.datagenerator.core.DataTypeParameters;
import com.sanjivsingh.datagenerator.core.datatype.BaseDataType;
import com.sanjivsingh.datagenerator.core.datatype.DataType;
import com.sanjivsingh.datagenerator.core.model.GeneratorContext;
import com.sanjivsingh.datagenerator.core.model.MACAddress;

public class DMACAddress extends BaseDataType {

	private static final int NUM_OF_FIELDS = 6;
	private char c = DataTypeDefaults.MACADDRESS_SEPERATOR;
	private boolean isCap = DataTypeDefaults.MACADDRESS_IS_CAPITAL;

	private void setC(char c) {
		this.c = c;
	}

	private void setCap(boolean isCap) {
		this.isCap = isCap;
	}

	private DMACAddress(int charIndex, int recordIndex,
			GeneratorContext generatorContext) {
		super(charIndex, recordIndex, generatorContext);
	}

	@Override
	public String getRandomValue() {

		byte arr[] = new byte[NUM_OF_FIELDS];
		Random r = new Random();
		for (int i = 0; i < NUM_OF_FIELDS; i++) {
			arr[i] = (byte) r.nextInt();
		}
		MACAddress h = new MACAddress(arr);
		return (isCap) ? h.toReadbleString(c).toUpperCase() : h
				.toReadbleString(c).toUpperCase();

	}

	public static DataType getInstance(Integer charIndex, Integer recordIndex,
			GeneratorContext generatorContext) {

		DMACAddress dMACAddress = new DMACAddress(charIndex, recordIndex,
				generatorContext);
		if (dMACAddress.getParameterMap().containsKey(
				DataTypeParameters.MACADDRESS_SEPERATOR)) {
			dMACAddress.setC(((String) dMACAddress.getParameterMap().get(
					DataTypeParameters.MACADDRESS_SEPERATOR)).toCharArray()[0]);
		}
		if (dMACAddress.getParameterMap().containsKey(
				DataTypeParameters.MACADDRESS_IS_CAPITAL)) {
			dMACAddress.setCap(getBoolean(dMACAddress.getParameterMap(),
					DataTypeParameters.MACADDRESS_IS_CAPITAL));
		}
		return dMACAddress;
	}

}
