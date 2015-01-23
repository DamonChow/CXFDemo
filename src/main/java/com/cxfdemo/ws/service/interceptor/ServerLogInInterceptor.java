package com.cxfdemo.ws.service.interceptor;

import java.util.Date;
import java.util.List;

import javax.security.auth.Subject;
import javax.xml.namespace.QName;

import org.apache.cxf.binding.soap.SoapMessage;
import org.apache.cxf.binding.soap.interceptor.AbstractSoapInterceptor;
import org.apache.cxf.common.security.SecurityToken;
import org.apache.cxf.common.security.UsernameToken;
import org.apache.cxf.headers.Header;
import org.apache.cxf.headers.Header.Direction;
import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.phase.Phase;
import org.apache.cxf.security.SecurityContext;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class ServerLogInInterceptor extends  AbstractSoapInterceptor {

	public void handleMessage(SoapMessage message) throws Fault {
		getMessage(message);
	}

	public ServerLogInInterceptor() {
		//一定要指定这个拦截器放在拦截器链的哪个阶段，这里放在最开始阶段。  
        super(Phase.PRE_INVOKE);
	}
	
	
	private void getMessage(SoapMessage message) {
		System.out.println("--------------log start-------------");
		System.out.println("message=" + message);
		System.out.println("getId=" + message.getId());
		System.out.println("getVersion=" + message.getVersion());
        //获取SOAP消息的Header  
        List<Header> headers = message.getHeaders();  
        //如果没有Header  
        if(headers == null || headers.size() < 1) {  
            throw new Fault(new IllegalArgumentException("没有Header,拦截器实施拦截"));  
        }  
        //获取Header携带是用户和密码信息  
        Header firstHeader = headers.get(0);  
        Element ele = (Element) firstHeader.getObject();  
          
        NodeList userIdEle = ele.getElementsByTagName("wsse:Username");  
        NodeList userPassEle = ele.getElementsByTagName("wsse:Password");  
          
        if (userIdEle.getLength() != 1) {  
            throw new Fault(new IllegalArgumentException("用户Id格式不对"));  
        }  
              
        if (userPassEle.getLength() != 1) {  
            throw new Fault(new IllegalArgumentException("用户密码格式不对"));  
        }  
          
        //获取元素的文本内容  
        String userId = userIdEle.item(0).getTextContent();  
        String userPass = userPassEle.item(0).getTextContent();  
        System.out.println("wsse:Username=" + userId);
        System.out.println("wsse:Password=" + userPass);
        if (!userId.equals("client") || !userPass.equals("clientpass")) {  
            throw new Fault(new IllegalArgumentException("用户和密码不正确"));  
        }  
		System.out.println("--------------log end-------------" + new Date());
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
}
