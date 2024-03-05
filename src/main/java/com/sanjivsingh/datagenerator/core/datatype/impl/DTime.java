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

public class DTime extends BaseDataType {

	private boolean format = DataTypeDefaults.TIME_FORMAT;
	private String from = DataTypeDefaults.TIME_FROM;
	private String to = DataTypeDefaults.TIME_TO;

	private DTime(int charIndex, int recordIndex,
			GeneratorContext generatorContext) {
		super(charIndex, recordIndex, generatorContext);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String getRandomValue() {

		String formatString = (format) ? Format.HOURS12.toString()
				: Format.HOURS24.toString();
		DateFormat formatter = new SimpleDateFormat(formatString);
		Calendar cal = Calendar.getInstance();
		Date fromTime = parse(from);
		Date toTime = parse(to);
		String str_from = formatter.format(fromTime);
		String str_to = formatter.format(toTime);

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

	private Date parse(String timeString) {
		DateFormat formatter = new SimpleDateFormat(Format.HOURS12.toString());
		Date date = null;
		try {
			date = formatter.parse(timeString);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return date;
	}

	public static DataType getInstance(Integer charIndex, Integer recordIndex,
			GeneratorContext generatorContext) {
		DTime dc = new DTime(charIndex, recordIndex, generatorContext);
		if (dc.getParameterMap().containsKey(DataTypeParameters.TIME_FORMAT)) {
			dc.setFormat(getBoolean(dc.getParameterMap(),
					DataTypeParameters.TIME_FORMAT));
		}
		if (dc.getParameterMap().containsKey(DataTypeParameters.TIME_FROM)) {
			dc.setFrom((String) dc.getParameterMap().get(
					DataTypeParameters.TIME_FROM));
		}
		if (dc.getParameterMap().containsKey(DataTypeParameters.TIME_TO)) {
			dc.setTo((String) dc.getParameterMap().get(
					DataTypeParameters.TIME_TO));
		}
		return dc;
	}

	private void setFormat(boolean format) {
		this.format = format;
	}

	private void setFrom(String from) {
		this.from = from;
	}

	private void setTo(String to) {
		this.to = to;
	}

	private enum Format {
		HOURS12("h:mm a"), HOURS24("H:mm");
		String format;

		private Format(String format) {
			this.format = format;
		}

		@Override
		public String toString() {
			return format;
		}

	}

}