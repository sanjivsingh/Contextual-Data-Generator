package com.sanjivsingh.datagenerator.core.datatype;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * @author Sanjiv.Singh
 * 
 */
public enum DataTypes {

	DATE(1, "DATE", "DATE datatype"), BIGNUMBER(2, "BIGNUMBER",
			"BIGNUMBER datatype"), ALPHANUMERIC(3, "ALPHANUMERIC", ""), BOOLEAN(
			4, "BOOLEAN", ""), COLOR(5, "COLOR", ""), COMPANY(6, "COMPANY", ""), COUNTRY(
			7, "COUNTRY", ""), COUNTRYCODE(8, "COUNTRYCODE", ""), CURRENCY(9,
			"CURRENCY", ""), CURRENCYCODE(10, "CURRENCYCODE", ""), FREQUENCY(
			11, "FREQUENCY", ""), GENDER(12, "GENDER", ""), GUID(13, "GUID", ""), HEXCOLOR(
			14, "HEXCOLOR", ""), DIPV4(15, "DIPV4", ""), DIPV6(16, "DIPV6", ""), LATITUDE(
			17, "LATITUDE", ""), LONGITUDE(18, "LONGITUDE", ""), MACADDRESS(19,
			"MACADDRESS", ""), ROWNUMBER(20, "ROWNUMBER", ""), TIME(21, "TIME",
			""), TIMEZONE(22, "TIMEZONE", ""), TITLE(23, "TITLE", ""), MONEY(
			24, "MONEY", ""), REGULAREXPRESSION(25, "REGULAREXPRESSION", ""), CUSTOMLIST(
			26, "CUSTOMLIST", ""), TOPDOMAINNAME(27, "TOPDOMAINNAME", ""), DOMAINNAME(
			28, "DOMAINNAME", ""), EMAIL(29, "EMAIL", ""), URL(30, "URL", ""), INDEX(
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
	 * @param c
	 *            the c
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
	 * @return the code
	 */
	public Integer getCode() {
		return code;
	}

	/**
	 * @return the label
	 */
	public String getLabel() {
		return label;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

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