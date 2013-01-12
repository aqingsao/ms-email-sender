package com.thoughtworks.ms.email;

import java.io.File;
import java.net.URL;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.activation.MimetypesFileTypeMap;
import javax.activation.URLDataSource;

public class SMTPAttachment {
	/** File name*/
	private String fileName;
	
	/** Data Handler*/
	private DataHandler dataHandler;

	/**
	 * Constructor
	 * @param path
	 */
	public SMTPAttachment(String path) {
		this.fileName = new File(path).getName();
		MimetypesFileTypeMap mimeMap = new MimetypesFileTypeMap();
		mimeMap.addMimeTypes(mimeMap.getContentType(new File(path)));
		
		FileDataSource datasource = new FileDataSource(path);
		datasource.setFileTypeMap(mimeMap);
		this.dataHandler = new DataHandler(datasource);
	}

	/**
	 * Constructor
	 * @param url
	 * @param fileName
	 */
	public SMTPAttachment(URL url, String fileName) {
		this.fileName = fileName;
		this.dataHandler = new DataHandler(new URLDataSource(url));
	}

	/**
	 * get DataHandler
	 * @return
	 */
	public DataHandler getDataHandler() {
		return dataHandler;
	}

	/**
	 * get File Name
	 * @return
	 */
	public String getFileName() {
		return fileName;
	}

	/**
	 * set Datahandler
	 * @param dataHandler
	 */
	public void setDataHandler(DataHandler dataHandler) {
		this.dataHandler = dataHandler;
	}

	/**
	 * set file name
	 * @param fileName
	 */
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
}
