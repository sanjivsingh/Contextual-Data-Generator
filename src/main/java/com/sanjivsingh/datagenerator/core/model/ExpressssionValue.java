package com.sanjivsingh.datagenerator.core.model;

public class ExpressssionValue {

	private String expression;
	private String value;

	public ExpressssionValue(String expression, String value) {
		super();
		this.expression = expression;
		this.value = value;
	}

	public String getExpression() {
		return expression;
	}

	public void setExpression(String expression) {
		this.expression = expression;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
	
}
