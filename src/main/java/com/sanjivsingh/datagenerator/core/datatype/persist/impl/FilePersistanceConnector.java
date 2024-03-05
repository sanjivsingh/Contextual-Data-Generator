package com.sanjivsingh.datagenerator.core.datatype.persist.impl;

import java.io.BufferedWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.sanjivsingh.datagenerator.common.PropertiesManager;
import com.sanjivsingh.datagenerator.core.datatype.DGFileReadContentHandler;
import com.sanjivsingh.datagenerator.core.datatype.persist.ConnectorTypes;
import com.sanjivsingh.datagenerator.exception.DataGenException;
import com.sanjivsingh.datagenerator.exception.DataGenFileWriteExceptionTemplate;
import com.sanjivsingh.datagenerator.exception.impl.ExceptionHandlerImp;

public class FilePersistanceConnector extends GenericPersistanceConnector {

	private String inputDir;
	private String outputDir;
	private String seperator = System.getProperty("line.separator");
	private String ls = System.getProperty("line.separator");
	private String writePath;

	public String getWritePath() {
		return writePath;
	}

	public FilePersistanceConnector(String inputDir, String outputDir, String seperator) {
		super(ConnectorTypes.FILE);
		this.inputDir = inputDir;
		this.outputDir = outputDir;
		this.seperator = seperator;
	}

	@Override
	public void generateAndSave(List<String> records) {

		final String inputDirPath = inputDir;
		final String outputDirPath = outputDir;
		final List<String> recordsTemp = records;

		final String footerFile = PropertiesManager.getConfigurations().getValue("user.input.footer.filename");
		final String headerFile = PropertiesManager.getConfigurations().getValue("user.input.header.filename");

		String fileName = getDateFileName();
		writePath = outputDirPath + fileName;
		new DataGenFileWriteExceptionTemplate() {

			@Override
			public void writeProcess(BufferedWriter writer) {
				DGFileReadContentHandler dgFileHanlder = new DGFileReadContentHandler();

				try {
					// add header....
					try {
						String headerContent = dgFileHanlder.handleReadFile(inputDirPath + headerFile);
						writer.write(headerContent + ls);
					} catch (DataGenException e) {
						System.err.println("No header template available at input directory");
					}

					// add random data
					for (int i = 0; i < recordsTemp.size(); i++) {
						if (i == recordsTemp.size() - 1) {
							writer.write(recordsTemp.get(i));
						} else {
							writer.write(recordsTemp.get(i) + seperator);
						}
					}

					// add footer
					try {

						String footerContent = dgFileHanlder.handleReadFile(inputDirPath + footerFile);
						writer.write(ls + footerContent);
					} catch (DataGenException e) {
						System.err.println("No footer template available at input directory");
					}
				} catch (IOException writeException) {
					new ExceptionHandlerImp().handle(writeException, "Error processing InputStream for file" + writePath);
				}
			}

		}.handleWriteFile(writePath);
	}

	public String getOutputDir() {
		return outputDir;
	}

	public void setOutputDir(String outputDir) {
		this.outputDir = outputDir;
	}

	public String getSeperator() {
		return seperator;
	}

	public void setSeperator(String seperator) {
		this.seperator = seperator;
	}

	public static String getDateFileName() {
		SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
		Date now = new Date();
		String strDate = sdfDate.format(now);
		String fileName = "sampleData-" + strDate + ".txt";
		return fileName;
	}

}
