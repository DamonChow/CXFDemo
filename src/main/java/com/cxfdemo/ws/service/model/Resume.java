package com.cxfdemo.ws.service.model;

import java.io.Serializable;

import javax.activation.DataHandler;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlMimeType;

@XmlAccessorType(XmlAccessType.FIELD)
public class Resume implements Serializable{

	private static final long serialVersionUID = 109948021447801307L;
	private String candidateName;
	private String resumeFileType;
	
	@XmlMimeType("application/octet-stream")
	private DataHandler dataHandler;

	public String getCandidateName() {
		return candidateName;
	}

	public Resume() {
		super();
	}

	public void setCandidateName(String candidateName) {
		this.candidateName = candidateName;
	}

	public String getResumeFileType() {
		return resumeFileType;
	}

	public void setResumeFileType(String resumeFileType) {
		this.resumeFileType = resumeFileType;
	}

	public DataHandler getDataHandler() {
		return dataHandler;
	}

	public void setDataHandler(DataHandler dataHandler) {
		this.dataHandler = dataHandler;
	}

	@Override
	public String toString() {
		return "Resume [candidateName=" + candidateName + ", resumeFileType="
				+ resumeFileType + ", dataHandler=" + dataHandler + "]";
	}

	public Resume(String candidateName, String resumeFileType,
			DataHandler dataHandler) {
		super();
		this.candidateName = candidateName;
		this.resumeFileType = resumeFileType;
		this.dataHandler = dataHandler;
	}
}
