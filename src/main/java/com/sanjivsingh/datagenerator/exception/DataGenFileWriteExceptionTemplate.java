package com.sanjivsingh.datagenerator.exception;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/**
 * @author Sanjiv.Singh
 * 
 */
public abstract class DataGenFileWriteExceptionTemplate {

	public void handleWriteFile(String filePath) {

		IOException writeException = null;
		BufferedWriter writer = null;
		try {
			FileWriter fileWriter = new FileWriter(filePath);
			writer = new BufferedWriter(fileWriter);

			writeProcess(writer);
		} catch (IOException ex) {
			writeException = ex;
		} finally {
			if (writer != null) {
				try {
					writer.close();
				} catch (IOException e) {
					if (writeException != null) {
						throw new DataGenException("Error in opening file :  "
								+ filePath, writeException);
					} else {
						throw new DataGenException(
								"Error closing InputStream for file "
										+ filePath, e);
					}
				}
			}
		}
		if (writeException != null) {
			throw new DataGenException("Error processing InputStream for file"
					+ filePath, writeException);
		}
	}

	public abstract void writeProcess(BufferedWriter writer);

}
