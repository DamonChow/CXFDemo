package com.cxfdemo.ws.client;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.cxfdemo.ws.service.HelloWorld;
import com.cxfdemo.ws.service.model.User;

public class Test {
	public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("client_Spring.xml");
        HelloWorld helloService = context.getBean("client",HelloWorld.class);
        /*String response = helloService.sayHi("Peter");
        System.out.println(response);*/
        /*System.out.println(helloService.getUser("2"));*/
       /*JaxWsProxyFactoryBean factory = context.getBean("clientFactory",JaxWsProxyFactoryBean.class);
        HelloWorld helloService = (HelloWorld) factory.create();
        String response = helloService.sayHi("Peter");*/
        
        
        /*Resume resume = new Resume();  
        resume.setCandidateName("tx");  
        resume.setResumeFileType("png");  
        DataSource source = new FileDataSource(new File("C:\\Users\\ASUS\\Desktop\\yl.png"));  
        resume.setDataHandler(new DataHandler(source));
        helloService.saveResumes(resume);*/
        
        
        User u = new User();
        u.setId("id");
        u.setName("name");
        u.setSex(2);
        List<String> collections = new ArrayList<String>();
        Map<String, String> map = new HashMap<String, String>();
        collections.add("11dfdfd");
        collections.add("22dfdfd");
        collections.add("33dfdfd");
        map.put("11", "333");
        map.put("22", "333");
        map.put("33", "333");
        map.put("44", "333");
        map.put("55", "333");
        u.setCollections(collections);
        u.setMap(map);
        helloService.saveUsers(u);
    }
}
