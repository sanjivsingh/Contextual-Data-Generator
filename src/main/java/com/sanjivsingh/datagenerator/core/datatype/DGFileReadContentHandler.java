package com.sanjivsingh.datagenerator.core.datatype;

import java.io.BufferedReader;
import java.io.IOException;

import com.sanjivsingh.datagenerator.exception.DataGenFileReadExceptionTemplate;

/**
 * The Class DGFileReadContentHandler.
 *
 * @author Sanjiv.Singh
 */
public class DGFileReadContentHandler extends DataGenFileReadExceptionTemplate {

	/**
	 * Read process.
	 *
	 * @param reader the reader
	 * @return the string
	 */
	@Override
	public String readProcess(BufferedReader reader) {
		String line = null;
		StringBuilder stringBuilder = new StringBuilder();
		String ls = System.getProperty("line.separator");

		boolean flag  =  false;				
		try {
			while ((line = reader.readLine()) != null) {
				if(flag){
					stringBuilder.append(ls);
				}
				stringBuilder.append(line);
				flag  =  true;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return stringBuilder.toString();
	}

}
