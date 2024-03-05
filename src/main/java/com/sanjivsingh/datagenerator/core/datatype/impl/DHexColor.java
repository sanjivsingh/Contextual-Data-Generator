package com.sanjivsingh.datagenerator.core.datatype.impl;

import java.awt.Color;
import java.util.Random;

import com.sanjivsingh.datagenerator.core.datatype.BaseDataType;
import com.sanjivsingh.datagenerator.core.datatype.DataType;
import com.sanjivsingh.datagenerator.core.model.GeneratorContext;

public class DHexColor extends BaseDataType {

	@Override
	public String getRandomValue() {
		Random random = new Random();

		int red = random.nextInt(256);
		int green = random.nextInt(256);
		int blue = random.nextInt(256);
		Color randomColor = new Color(red, green, blue);

		String hex = "#"
				+ Integer.toHexString(randomColor.getRGB()).substring(2);
		return hex;
	}

	public static DataType getInstance(Integer charIndex, Integer recordIndex,
			GeneratorContext generatorContext) {

		DHexColor dHexColor = new DHexColor(charIndex, recordIndex,
				generatorContext);
		return dHexColor;
	}

	private DHexColor(int charIndex, int recordIndex,
			GeneratorContext generatorContext) {
		super(charIndex, recordIndex, generatorContext);
		// TODO Auto-generated constructor stub
	}

}
