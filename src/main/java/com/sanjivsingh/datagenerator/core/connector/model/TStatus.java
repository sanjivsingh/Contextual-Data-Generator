package com.sanjivsingh.datagenerator.core.connector.model;

/**
 * @author Sanjiv.Singh
 * 
 */
public class TStatus {

	private boolean status = true;
	private String message = "";

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("TStatus");
		sb.append("{status=").append(status);
		sb.append(", message='").append(message).append('\'');
		sb.append('}');
		return sb.toString();
	}
}
