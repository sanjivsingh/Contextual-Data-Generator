package com.sanjivsingh.datagenerator.exception;

public class DataGenRuntimeException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8826162764905971866L;
	private String message = "";
	private Exception exception;

	public DataGenRuntimeException(String message) {
		super();
		this.message = message;
	}

	public DataGenRuntimeException(String message, Exception exception) {
		super();
		this.message = message;
		this.exception = exception;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return message + " " + exception;
	}

}
