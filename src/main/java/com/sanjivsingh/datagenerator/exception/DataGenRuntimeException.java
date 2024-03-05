package com.sanjivsingh.datagenerator.exception;

/**
 * The Class DataGenRuntimeException.
 */
public class DataGenRuntimeException extends RuntimeException {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 8826162764905971866L;
	
	/** The message. */
	private String message = "";
	
	/** The exception. */
	private Exception exception;

	/**
	 * Instantiates a new data gen runtime exception.
	 *
	 * @param message the message
	 */
	public DataGenRuntimeException(String message) {
		super();
		this.message = message;
	}

	/**
	 * Instantiates a new data gen runtime exception.
	 *
	 * @param message the message
	 * @param exception the exception
	 */
	public DataGenRuntimeException(String message, Exception exception) {
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
		// TODO Auto-generated method stub
		return message + " " + exception;
	}

}
