package com.cxfdemo.ws.service;

import java.util.List;

import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

import com.cxfdemo.ws.service.model.Resume;
import com.cxfdemo.ws.service.model.User;

@WebService
public interface HelloWorld {

	@WebResult(name = "String")
	public String sayHi(@WebParam(name="text")String text);
	
	@WebResult(name = "user")
	public User getUser(@WebParam(name="id")String id);
	
	public List<User> getAllUsers();
	
	public void saveUser(@WebParam(name="id")String id, 
			@WebParam(name="name")String name, 
			@WebParam(name="sex")int sex);
	
	/**
	 * 客户端的ObjectFactory的createUser方法的参数必须和User的构造函数的参数一致
	 * <p>
	 * 如客户端中需要用到new User("id","name",1)构造User对象时
	 * 
	 * 需要在ObjectFactory中加入
	 * 	public User createUser(String id,String name,int sex) {
     * 		return new User(id,name,sex);
     *	}
	 * </p>
	 * @param user
	 */
	public void saveUsers(@WebParam(name="user")User user);
	
	@WebResult(name = "String")
	public String saveResumes(@WebParam(name="resume")Resume resume); 
	
}
