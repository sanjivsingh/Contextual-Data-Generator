package com.sanjivsingh.datagenerator.core.datatype.impl;

import java.text.DecimalFormat;

import com.sanjivsingh.datagenerator.core.DataTypeDefaults;
import com.sanjivsingh.datagenerator.core.DataTypeParameters;
import com.sanjivsingh.datagenerator.core.datatype.BaseDataType;
import com.sanjivsingh.datagenerator.core.datatype.DataType;
import com.sanjivsingh.datagenerator.core.model.GeneratorContext;
import com.sanjivsingh.datagenerator.core.util.RandomUtil;

public class DMoney extends BaseDataType {

	private String[] cs = { "¥", "£", "₹", "$", "€", "₪", "₭" };

	private String currencySymbol = DataTypeDefaults.MONEY_CURRENCY_SYMBOL;
	private int decimal = DataTypeDefaults.MONEY_DECIMAL;
	private String start = DataTypeDefaults.MONEY_START;
	private String end = DataTypeDefaults.MONEY_END;

	private DMoney(int charIndex, int recordIndex,
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
		String formatedNum = df.format(number);

		if (currencySymbol.equals("None")) {
			return formatedNum;
		} else if (currencySymbol.equals("Random")) {
			return getRandomCurencySymbol() + formatedNum;
		}
		return currencySymbol + formatedNum;
	}

	private String getRandomCurencySymbol() {
		return cs[RandomUtil.nextInt(0, cs.length - 1)];
	}

	private String getFormat(int decimal) {
		String returnStr = "##,##,###";
		if (decimal > 0) {
			returnStr = returnStr + ".";
			for (int i = 1; i <= decimal; i++) {
				returnStr = returnStr + "0";
			}
		}
		System.out.println(returnStr);
		return returnStr;
	}

	public static DataType getInstance(Integer charIndex, Integer recordIndex,
			GeneratorContext generatorContext) {

		DMoney dMoney = new DMoney(charIndex, recordIndex, generatorContext);
		if (dMoney.getParameterMap().containsKey(
				DataTypeParameters.MONEY_DECIMAL)) {
			dMoney.setDecimal(getInt(dMoney.getParameterMap(),
					DataTypeParameters.MONEY_DECIMAL));
		}
		if (dMoney.getParameterMap().containsKey(
				DataTypeParameters.MONEY_CURRENCY_SYMBOL)) {
			dMoney.setCurrencySymbol((String) dMoney.getParameterMap().get(
					DataTypeParameters.MONEY_CURRENCY_SYMBOL));
		}
		if (dMoney.getParameterMap()
				.containsKey(DataTypeParameters.MONEY_START)) {
			dMoney.setStart((String) dMoney.getParameterMap().get(
					DataTypeParameters.MONEY_START));
		}
		if (dMoney.getParameterMap().containsKey(DataTypeParameters.MONEY_END)) {
			dMoney.setEnd((String) dMoney.getParameterMap().get(
					DataTypeParameters.MONEY_END));
		}
		return dMoney;
	}

	private void setCurrencySymbol(String currencySymbol) {
		this.currencySymbol = currencySymbol;
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
