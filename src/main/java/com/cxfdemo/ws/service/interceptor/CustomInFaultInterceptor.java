package com.cxfdemo.ws.service.interceptor;

import org.apache.cxf.message.Message;
import org.apache.cxf.phase.AbstractPhaseInterceptor;

public class CustomInFaultInterceptor extends AbstractPhaseInterceptor<Message> {

	public CustomInFaultInterceptor(String phase) {

		super(phase);
	}

	public void handleMessage(Message message) {

		System.out.println("getInMessage------------------"
				+ message.getExchange().getInMessage());
		System.out.println("getInFaultMessage------------------"
				+ message.getExchange().getInFaultMessage());
		System.out.println("getOutMessage------------------"
				+ message.getExchange().getOutMessage());
		System.out.println("getOutFaultMessage------------------"
				+ message.getExchange().getOutFaultMessage());
	}

}