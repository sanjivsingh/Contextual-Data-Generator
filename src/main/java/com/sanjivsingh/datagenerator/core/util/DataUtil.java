/*
 * 
 */
package com.sanjivsingh.datagenerator.core.util;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import com.sanjivsingh.datagenerator.core.DataTypeParameters;

/**
 * The Class DataUtil.
 *
 * @author Sanjiv.Singh
 */
public class DataUtil {

	/**
	 * Format C.
	 *
	 * @param c the c
	 * @return the int
	 */
	public static int formatC(int c) {
		if (c == 258) {
			c = '.' & 0xFF;
		}
		return c;
	}

	/**
	 * Format regex.
	 *
	 * @param regex the regex
	 * @return the string
	 */
	public static String formatRegex(String regex) {
		// regex = regex.replaceAll("\\.",
		// String.valueOf(Character.toChars(Symbals.L_PRAN_SYMBOL_INDEX));
		return regex;
	}

	/**
	 * Gets the map.
	 *
	 * @param expression the expression
	 * @return the map
	 */
	public static Map<String, Object> getMap(String expression) {

		HashMap<String, Object> result = null;
		String temp = expression.substring(1, expression.length());
		try {
			result = new ObjectMapper().readValue(temp, HashMap.class);
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * Gets the id.
	 *
	 * @param expression the expression
	 * @return the id
	 */
	public static String getId(String expression) {
		Map<String, Object> map = getMap(expression);
		if (map.containsKey(DataTypeParameters.ID_FIELD)) {
			return (String) map.get(DataTypeParameters.ID_FIELD);
		}
		return null;
	}

	/**
	 * Gets the date string.
	 *
	 * @param date the date
	 * @return the date string
	 */
	public static String getDateString(Date date) {
		SimpleDateFormat sdfDate = new SimpleDateFormat("hh:mm:ss.SSS");
		String strDate = sdfDate.format(date);
		return strDate;

	}

	/**
	 * Gets the date diff.
	 *
	 * @param end the end
	 * @param start the start
	 * @return the date diff
	 */
	public static String getDateDiff(Date end, Date start) {
		// in milliseconds
		long diff = end.getTime() - start.getTime();

		long diffSeconds = diff / 1000 % 60;
		long diffMinutes = diff / (60 * 1000) % 60;
		long diffHours = diff / (60 * 60 * 1000) % 24;
		long diffDays = diff / (24 * 60 * 60 * 1000);

		return diffHours + ":" + diffMinutes + ":" + diffSeconds;
	}

}
