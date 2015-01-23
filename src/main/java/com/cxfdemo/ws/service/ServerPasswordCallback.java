package com.cxfdemo.ws.service;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.UnsupportedCallbackException;

import org.apache.ws.security.WSPasswordCallback;
 
public class ServerPasswordCallback implements CallbackHandler {
 
    private static final Map<String, String> userMap = new HashMap<String, String>();
 
    static {
        userMap.put("client", "clientpass");
        userMap.put("server", "serverpass");
    }
 
    public void handle(Callback[] callbacks) throws IOException, UnsupportedCallbackException {
        WSPasswordCallback callback = (WSPasswordCallback) callbacks[0];
        //实际上 callback.getPassword()是为null
        String clientUsername = callback.getIdentifier();
        //其实这一步是应该从数据库中取密码在设置到callback中
        String serverPassword = userMap.get(clientUsername);
        if (serverPassword != null) {
            callback.setPassword(serverPassword);
        }
        
        try {
        	System.out.println(new Date());
        	Thread.sleep(1000L);
        } catch (InterruptedException e) {
        	//nothing.
        }
    }
}