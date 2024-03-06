package com.sanjivsingh.datagenerator.core.datatype;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * The Enum DataTypes.
 *
 * @author Sanjiv.Singh
 */
public enum DataTypes {

	/** The date. */
	DATE(1, "DATE", "DATE datatype"), /** The bignumber. */
 BIGNUMBER(2, "BIGNUMBER",
			"BIGNUMBER datatype"), 
 /** The alphanumeric. */
 ALPHANUMERIC(3, "ALPHANUMERIC", ""), 
 /** The boolean. */
 BOOLEAN(
			4, "BOOLEAN", ""), 
 /** The color. */
 COLOR(5, "COLOR", ""), 
 /** The company. */
 COMPANY(6, "COMPANY", ""), 
 /** The country. */
 COUNTRY(
			7, "COUNTRY", ""), 
 /** The countrycode. */
 COUNTRYCODE(8, "COUNTRYCODE", ""), 
 /** The currency. */
 CURRENCY(9,
			"CURRENCY", ""), 
 /** The currencycode. */
 CURRENCYCODE(10, "CURRENCYCODE", ""), 
 /** The frequency. */
 FREQUENCY(
			11, "FREQUENCY", ""), 
 /** The gender. */
 GENDER(12, "GENDER", ""), 
 /** The guid. */
 GUID(13, "GUID", ""), 
 /** The hexcolor. */
 HEXCOLOR(
			14, "HEXCOLOR", ""), 
 /** The dipv4. */
 DIPV4(15, "DIPV4", ""), 
 /** The dipv6. */
 DIPV6(16, "DIPV6", ""), 
 /** The latitude. */
 LATITUDE(
			17, "LATITUDE", ""), 
 /** The longitude. */
 LONGITUDE(18, "LONGITUDE", ""), 
 /** The macaddress. */
 MACADDRESS(19,
			"MACADDRESS", ""), 
 /** The rownumber. */
 ROWNUMBER(20, "ROWNUMBER", ""), 
 /** The time. */
 TIME(21, "TIME",
			""), 
 /** The timezone. */
 TIMEZONE(22, "TIMEZONE", ""), 
 /** The title. */
 TITLE(23, "TITLE", ""), 
 /** The money. */
 MONEY(
			24, "MONEY", ""), 
 /** The regularexpression. */
 REGULAREXPRESSION(25, "REGULAREXPRESSION", ""), 
 /** The customlist. */
 CUSTOMLIST(
			26, "CUSTOMLIST", ""), 
 /** The topdomainname. */
 TOPDOMAINNAME(27, "TOPDOMAINNAME", ""), 
 /** The domainname. */
 DOMAINNAME(
			28, "DOMAINNAME", ""), 
 /** The email. */
 EMAIL(29, "EMAIL", ""), 
 /** The url. */
 URL(30, "URL", ""), 
 /** The index. */
 INDEX(
			31, "INDEX", "");

	/** The code. */
	private int code;

	/** The label. */
	private String label;

	/** The description. */
	private String description;

	/** The code to flow mapping. */
	private static Map<Integer, DataTypes> codeToDataTypeMapping;

	/**
	 * Instantiates a new DataType.
	 *
	 * @param c            the c
	 * @param label the label
	 * @param description the description
	 */
	private DataTypes(Integer c, String label, String description) {
		this.code = c;
		this.label = label;
		this.description = description;
	}

	/**
	 * Gets the DataType.
	 * 
	 * @param i
	 *            the i
	 * @return the DataType
	 */
	public static DataTypes getDataType(int i) {
		if (codeToDataTypeMapping == null) {
			initMapping();
		}
		return codeToDataTypeMapping.get(i);
	}

	/**
	 * Gets the DataType.
	 * 
	 * @param label
	 *            the label
	 * @return the DataType
	 */
	public static DataTypes getDataType(String label) {
		if (codeToDataTypeMapping == null) {
			initMapping();
		}
		Iterator<Integer> iterator = codeToDataTypeMapping.keySet().iterator();
		while (iterator.hasNext()) {
			Integer next = iterator.next();
			if (codeToDataTypeMapping.get(next).label.equalsIgnoreCase(label)) {
				return codeToDataTypeMapping.get(next);
			}
		}
		return null;
	}

	/**
	 * Inits the mapping.
	 */
	private static void initMapping() {
		codeToDataTypeMapping = new HashMap<Integer, DataTypes>();
		for (DataTypes s : DataTypes.values()) {
			codeToDataTypeMapping.put(s.code, s);
		}
	}

	/**
	 * Gets the code.
	 *
	 * @return the code
	 */
	public Integer getCode() {
		return code;
	}

	/**
	 * Gets the label.
	 *
	 * @return the label
	 */
	public String getLabel() {
		return label;
	}

	/**
	 * Gets the description.
	 *
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * To string.
	 *
	 * @return the string
	 */
	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Enum#toString()
	 */
	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("DataTypes");
		sb.append("{code=").append(code);
		sb.append(", label='").append(label).append('\'');
		sb.append(", description='").append(description).append('\'');
		sb.append('}');
		return sb.toString();
	}
}