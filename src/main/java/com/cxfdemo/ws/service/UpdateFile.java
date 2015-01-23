package com.cxfdemo.ws.service;

import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

import com.cxfdemo.ws.service.model.Resume;

@WebService
public interface UpdateFile {

	@WebResult(name = "String")
	public String saveResumes(@WebParam(name="resume")Resume resume); 
}
