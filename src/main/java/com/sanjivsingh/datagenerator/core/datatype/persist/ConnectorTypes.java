package com.sanjivsingh.datagenerator.core.datatype.persist;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public enum ConnectorTypes {

	CASSANDRA(1, "cassandra", ""), HBASE(2, "hbase", ""), MONGODB(3, "mongodb",
			""), RDBMS(4, "rdbms", ""), REDIS(5, "redis", ""), NEO4J(6,
			"neo4j", ""), ORACLE_KVSTORE(7, "oracle_kvstore", ""), COUCHDB(8,
			"couchdb", ""), KAFKA(9, "kafka", ""), RABBITMQ(10, "rabbitmq", ""), LIST(
			11, "list", ""), FILE(12, "file", "");

	/** The code. */
	private int code;

	/** The label. */
	private String label;

	/** The description. */
	private String description;

	/** The code to connector mapping. */
	private static Map<Integer, ConnectorTypes> codeToConnectorMapping;

	/**
	 * Instantiates a new ConnectorType.
	 * 
	 * @param c
	 *            the c
	 */
	private ConnectorTypes(Integer c, String label, String description) {
		this.code = c;
		this.label = label;
		this.description = description;
	}

	/**
	 * Gets the ConnectorType.
	 * 
	 * @param i
	 *            the i
	 * @return the ConnectorType
	 */
	public static ConnectorTypes getConnectorType(int i) {
		if (codeToConnectorMapping == null) {
			initMapping();
		}
		return codeToConnectorMapping.get(i);
	}

	/**
	 * Gets the ConnectorType.
	 * 
	 * @param label
	 *            the label
	 * @return the ConnectorType
	 */
	public static ConnectorTypes getConnectorType(String label) {
		if (codeToConnectorMapping == null) {
			initMapping();
		}
		Iterator<Integer> iterator = codeToConnectorMapping.keySet().iterator();
		while (iterator.hasNext()) {
			Integer next = iterator.next();
			if (codeToConnectorMapping.get(next).label.equalsIgnoreCase(label)) {
				return codeToConnectorMapping.get(next);
			}
		}
		return null;
	}

	/**
	 * Inits the mapping.
	 */
	private static void initMapping() {
		codeToConnectorMapping = new HashMap<Integer, ConnectorTypes>();
		for (ConnectorTypes s : ConnectorTypes.values()) {
			codeToConnectorMapping.put(s.code, s);
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
		sb.append("ConnectorType");
		sb.append("{code=").append(code);
		sb.append(", label='").append(label).append('\'');
		sb.append(", description='").append(description).append('\'');
		sb.append('}');
		return sb.toString();
	}

}
