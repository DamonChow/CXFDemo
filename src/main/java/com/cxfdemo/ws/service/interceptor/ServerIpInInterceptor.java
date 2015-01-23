package com.cxfdemo.ws.service.interceptor;

import javax.servlet.http.HttpServletRequest;

import org.apache.cxf.binding.soap.SoapMessage;
import org.apache.cxf.binding.soap.interceptor.AbstractSoapInterceptor;
import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.phase.Phase;
import org.apache.cxf.transport.http.AbstractHTTPDestination;

public class ServerIpInInterceptor extends  AbstractSoapInterceptor {

	public void handleMessage(SoapMessage message) throws Fault {
		HttpServletRequest request = (HttpServletRequest) message.get(AbstractHTTPDestination.HTTP_REQUEST);  
		String ipAddress = getIpAddr(request);
		System.out.println("i=================================ip");
		System.out.println("ipAddress======" +ipAddress);
		System.out.println("i=================================ip");
	}

	public ServerIpInInterceptor() {
		//一定要指定这个拦截器放在拦截器链的哪个阶段，这里放在最开始阶段。  
        super(Phase.READ);
	}
	
	public static String getIpAddr(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }
}
