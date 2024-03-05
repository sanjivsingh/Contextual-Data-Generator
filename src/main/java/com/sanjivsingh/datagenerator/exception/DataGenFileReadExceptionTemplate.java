package com.sanjivsingh.datagenerator.exception;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import com.sanjivsingh.datagenerator.exception.impl.ExceptionHandlerImp;

/**
 * @author Sanjiv.Singh
 * 
 */
public abstract class DataGenFileReadExceptionTemplate {

	public String handleReadFile(String filePath) {

		String fileContent = null;
		IOException processException = null;
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader(filePath));
			fileContent = readProcess(reader);
		} catch (IOException e) {
			processException = e;
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
					if (processException != null) {
						new ExceptionHandlerImp().handle(processException,
								"Error in opening file :  " + filePath);
					} else {
						new ExceptionHandlerImp().handle(e,
								"Error closing InputStream for file "
										+ filePath);
					}
				}
			}
		}
		if (processException != null) {
			new ExceptionHandlerImp().handle(processException,
					"Error processing InputStream for file" + filePath);
		}
		return fileContent;
	}

	public abstract String readProcess(BufferedReader bufferedReader);

}
