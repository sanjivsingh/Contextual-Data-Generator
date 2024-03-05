package com.sanjivsingh.datagenerator.exception;

/**
 * The Class DataGenException.
 *
 * @author Sanjiv.Singh
 */
public class DataGenException extends RuntimeException {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The message. */
	private String message = "";
	
	/** The exception. */
	private Exception exception;

	/**
	 * Instantiates a new data gen exception.
	 *
	 * @param message the message
	 */
	public DataGenException(String message) {
		super();
		this.message = message;
	}

	/**
	 * Instantiates a new data gen exception.
	 *
	 * @param message the message
	 * @param exception the exception
	 */
	public DataGenException(String message, Exception exception) {
		super();
		this.message = message;
		this.exception = exception;
	}

	/**
	 * To string.
	 *
	 * @return the string
	 */
	@Override
	public String toString() {
		return message + " " + exception;
	}

}
