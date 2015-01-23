package com.cxfdemo.ws.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.activation.DataHandler;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlMimeType;

import com.cxfdemo.ws.service.model.Resume;

@WebService(endpointInterface="com.cxfdemo.ws.service.UpdateFile",
serviceName="UpdateFileServiceService",portName="UpdateFileServicePort",
name="UpdateFileService",targetNamespace="http://service.ws.cxfdemo.com/")
public class UpdateFileService implements UpdateFile {

	public String saveResumes(@XmlMimeType("application/octet-stream")Resume resume) {
		DataHandler handler = resume.getDataHandler();
		try {
			System.out.println("2");
			InputStream is = handler.getInputStream();
			OutputStream os = new FileOutputStream(new File("D:\\"
					+ resume.getCandidateName() + "."
					+ resume.getResumeFileType()));
			byte[] b = new byte[100000];
			int bytesRead = 0;
			while ((bytesRead = is.read(b)) != -1) {
				os.write(b, 0, bytesRead);
			}
			System.out.println("3");
			os.flush();
			os.close();
			is.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "ok";
	}

}
