package com.thoughtworks.ms.email;

import java.io.File;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.activation.MimetypesFileTypeMap;
import javax.mail.MessagingException;
import javax.mail.internet.MimeBodyPart;

public class SMTPAttachment {
	private String fileName;
	
	private DataHandler dataHandler;

	public SMTPAttachment(String path) {
		this.fileName = new File(path).getName();
		MimetypesFileTypeMap mimeMap = new MimetypesFileTypeMap();
		mimeMap.addMimeTypes(mimeMap.getContentType(new File(path)));
		
		FileDataSource datasource = new FileDataSource(path);
		datasource.setFileTypeMap(mimeMap);
		this.dataHandler = new DataHandler(datasource);
	}

    MimeBodyPart asBodyPart() throws MessagingException {
        MimeBodyPart att = new MimeBodyPart();
        att.setDataHandler(dataHandler);
        att.setFileName(fileName);
        return att;
    }
}