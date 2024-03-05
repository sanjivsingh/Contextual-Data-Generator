package com.sanjivsingh.datagenerator.core.datatype.impl;

import com.sanjivsingh.datagenerator.core.datatype.BaseDataType;
import com.sanjivsingh.datagenerator.core.datatype.DataType;
import com.sanjivsingh.datagenerator.core.model.GeneratorContext;

public class DCreditCard extends BaseDataType {

	/*
	 * All credit card numbers are divided into 3 parts:
	 * 
	 * Issuer Identifier - 6 digits to identify the issuer of the card.
	 * 
	 * Acccount Number - 6 to 9 digits to identify the individual account
	 * number.
	 * 
	 * Check Digit - 1 digit computed as a checksum of the Issuer Identifier and
	 * the Account Number based on Luhn algorithm.
	 * 
	 * 
	 * http://www.getcreditcardnumbers.com/generated-credit-card-numbers
	 * 
	 * http://sqa.fyicenter.com/Online_Test_Tools/Test_Credit_Card_Number_Generator
	 * .php
	 */

	private DCreditCard(int charIndex, Integer recordIndex,
			GeneratorContext generatorContext) {
		super(charIndex, recordIndex, generatorContext);
		// TODO Auto-generated constructor stub
	}

	private String seperator = "-";
	private String type = "Visa";

	@Override
	public String getRandomValue() {
		// TODO Auto-generated method stub
		return null;
	}

	public static DataType getInstance(Integer charIndex,
			GeneratorContext generatorContext) {

		// TODO Auto-generated method stub
		return null;
	}

}
