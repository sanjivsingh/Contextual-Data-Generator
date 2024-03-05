package com.sanjivsingh.datagenerator.core.datatype.persist;

public class TypeTypeValue {

	private String type1;
	private String type2;
	private String value;

	public TypeTypeValue(String type1, String type2, String value) {
		super();
		this.type1 = type1;
		this.type2 = type2;
		this.value = value;
	}

	public String getType1() {
		return type1;
	}

	public void setType1(String type1) {
		this.type1 = type1;
	}

	public String getType2() {
		return type2;
	}

	public void setType2(String type2) {
		this.type2 = type2;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}
