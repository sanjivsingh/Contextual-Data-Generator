package com.sanjivsingh.datagenerator.core.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class GeneratorContext {

	
	private String uniqueID;
	
	private int totalRecords;
	
	/* store <Expression,Value> by charIndex */
	private Map<Integer, ExpressssionValue> dMap = new HashMap<Integer, ExpressssionValue>();

	/* store List<Expression,Value> by dataType id */
	private Map<String, ArrayList<ExpressssionValue>> expressionValuesById = new HashMap<String, ArrayList<ExpressssionValue>>();

	
	private IScope iScope;
	
	public GeneratorContext() {
		super();
	}

	public String getExpression(Integer index) {
		return dMap.get(index).getExpression();
	}

	public String getValue(Integer index) {
		return dMap.get(index).getValue();
	}

	public ExpressssionValue addExpression(Integer index, String expression) {
		ExpressssionValue expressssionValue = new ExpressssionValue(expression,
				null);
		dMap.put(index, expressssionValue);
		return expressssionValue;
	}

	public void addValue(Integer index, String value) {
		dMap.get(index).setValue(value);
	}

	public Set<Integer> getIndexs() {
		return dMap.keySet();
	}

	public void addExpresssionById(String id, ExpressssionValue expressionValue) {
		if (expressionValuesById.containsKey(id)) {
			expressionValuesById.get(id).add(expressionValue);
		} else {
			ArrayList<ExpressssionValue> values = new ArrayList<>();
			values.add(expressionValue);
			expressionValuesById.put(id, values);
		}
	}

	public ArrayList<ExpressssionValue> getExpresssionById(String id) {
		return expressionValuesById.get(id);
	}

	public Map<Integer, ExpressssionValue> getdMap() {
		return dMap;
	}

	public void setdMap(Map<Integer, ExpressssionValue> dMap) {
		this.dMap = dMap;
	}

	public Map<String, ArrayList<ExpressssionValue>> getExpressionValuesById() {
		return expressionValuesById;
	}

	public void setExpressionValuesById(
			Map<String, ArrayList<ExpressssionValue>> expressionValuesById) {
		this.expressionValuesById = expressionValuesById;
	}

	public IScope getIterationTree() {
		return iScope;
	}

	public void setIterationTree(IScope iScope) {
		this.iScope = iScope;
	}

	public String getUniqueID() {
		return uniqueID;
	}

	public void setUniqueID(String uniqueID) {
		this.uniqueID = uniqueID;
	}

	public int getTotalRecords() {
		return totalRecords;
	}

	public void setTotalRecords(int totalRecords) {
		this.totalRecords = totalRecords;
	}
}
