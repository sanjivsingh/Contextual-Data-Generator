package com.sanjivsingh.datagenerator.core.datatype.impl;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.sanjivsingh.datagenerator.core.DataTypeDefaults;
import com.sanjivsingh.datagenerator.core.DataTypeParameters;
import com.sanjivsingh.datagenerator.core.datatype.BaseDataType;
import com.sanjivsingh.datagenerator.core.datatype.DataType;
import com.sanjivsingh.datagenerator.core.model.GeneratorContext;

public class DDate extends BaseDataType {

	private String format = DataTypeDefaults.DATE_FORMAT;
	private Date from;
	private Date to;

	public void setFormat(String format) {
		this.format = format;
	}

	public void setFrom(Date from) {
		this.from = from;
	}

	public void setTo(Date to) {
		this.to = to;
	}

	private DDate(int charIndex, int recordIndex,
			GeneratorContext generatorContext) {
		super(charIndex, recordIndex, generatorContext);
	}

	@Override
	public String getRandomValue() {

		if (null == from || null == to) {
			to = new Date();

			Object clone = to.clone();
			from = (Date) clone;
			from.setYear(from.getYear() - 2);

		}

		DateFormat formatter = new SimpleDateFormat(format);
		Calendar cal = Calendar.getInstance();
		String str_from = formatter.format(from);
		String str_to = formatter.format(to);

		try {
			cal.setTime(formatter.parse(str_from));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Long value1 = cal.getTimeInMillis();

		try {
			cal.setTime(formatter.parse(str_to));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Long value2 = cal.getTimeInMillis();

		long value3 = (long) (value1 + Math.random() * (value2 - value1));
		cal.setTimeInMillis(value3);
		return formatter.format(cal.getTime());
	}

	public static DataType getInstance(Integer charIndex, Integer recordIndex,
			GeneratorContext generatorContext) {

		DDate dd = new DDate(charIndex, recordIndex, generatorContext);
		if (dd.getParameterMap().containsKey(DataTypeParameters.DATE_FORMAT)) {
			dd.setFormat((String) dd.getParameterMap().get(
					DataTypeParameters.DATE_FORMAT));
		}

		if (dd.getParameterMap().containsKey(DataTypeParameters.DATE_FROM)) {
			String fromString = (String) dd.getParameterMap().get(
					DataTypeParameters.DATE_FROM);
			dd.setFrom(new Date(fromString));

		}
		if (dd.getParameterMap().containsKey(DataTypeParameters.DATE_TO)) {
			String toString = (String) dd.getParameterMap().get(
					DataTypeParameters.DATE_TO);
			dd.setTo(new Date(toString));
		}
		return dd;
	}

}
