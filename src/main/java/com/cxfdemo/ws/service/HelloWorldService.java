package com.cxfdemo.ws.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.activation.DataHandler;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlMimeType;

import com.cxfdemo.ws.service.model.Resume;
import com.cxfdemo.ws.service.model.User;

@WebService(endpointInterface="com.cxfdemo.ws.service.HelloWorld",
serviceName="HelloWorldServiceService",portName="HelloWorldServicePort",
name="HelloWorldService",targetNamespace="http://service.ws.cxfdemo.com/")
public class HelloWorldService implements HelloWorld{

	public String sayHi(String text) {
		return "Hello " + text;
	}

	public User getUser(String id) {
		return new User("大王", id, 1);
	}

	public List<User> getAllUsers() {
		List<User> list = new ArrayList<User>();
		for (int i=0;i<4;i++) {
			list.add(new User("小明"+i,""+i,i));
		}
		return list;
	}

	public void saveUser(String id, String name, int sex) {
		System.out.println(new User(id, name, sex));
	}

	public void saveUsers(User user) {
		System.out.println(user);
	}

	public String saveResumes(@XmlMimeType("application/octet-stream")Resume resume) {
		if (resume == null) {
			throw new NullPointerException("参数非法.");
		}
		
		DataHandler handler = resume.getDataHandler();
		if (handler == null) {
			throw new NullPointerException("参数非法.");
		}
		OutputStream os = null;
		InputStream is = null;
		try {
			is = handler.getInputStream();
			os = new FileOutputStream(new File("D:\\"
					+ resume.getCandidateName() + "."
					+ resume.getResumeFileType()));
			byte[] b = new byte[100000];
			int bytesRead = 0;
			while ((bytesRead = is.read(b)) != -1) {
				os.write(b, 0, bytesRead);
			}
			os.flush();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (os != null) {
					os.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				if (is != null) {
					is.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return "ok";
	}
}
