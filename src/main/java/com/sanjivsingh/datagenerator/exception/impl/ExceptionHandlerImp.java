package com.sanjivsingh.datagenerator.exception.impl;

import com.sanjivsingh.datagenerator.exception.DataGenException;
import com.sanjivsingh.datagenerator.exception.ExceptionHandler;

/**
 * @author Sanjiv.Singh
 * 
 */
public class ExceptionHandlerImp implements ExceptionHandler {

	@Override
	public void handle(Exception ex, String errorMessage) {
		throw new DataGenException(errorMessage, ex);
	}

}
