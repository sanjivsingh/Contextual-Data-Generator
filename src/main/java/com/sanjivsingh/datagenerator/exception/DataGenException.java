package com.sanjivsingh.datagenerator.exception;

/**
 * @author Sanjiv.Singh
 * 
 */
public class DataGenException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String message = "";
	private Exception exception;

	public DataGenException(String message) {
		super();
		this.message = message;
	}

	public DataGenException(String message, Exception exception) {
		super();
		this.message = message;
		this.exception = exception;
	}

	@Override
	public String toString() {
		return message + " " + exception;
	}

}
