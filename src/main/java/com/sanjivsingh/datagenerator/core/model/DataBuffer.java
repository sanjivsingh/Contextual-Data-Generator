package com.sanjivsingh.datagenerator.core.model;

import java.util.List;

public class DataBuffer {

	private List<String> records;

	public List<String> getRecords() {
		return records;
	}

	public void setRecords(List<String> records) {
		this.records = records;
	}

	public boolean isEmpty() {
		if (null == records || records.isEmpty()) {
			return true;
		}
		return false;
	}

	public boolean isFull() {
		if (null != records && !records.isEmpty()) {
			return true;
		}
		return false;
	}

}
