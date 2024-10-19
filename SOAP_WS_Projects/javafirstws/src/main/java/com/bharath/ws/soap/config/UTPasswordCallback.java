package com.bharath.ws.soap.config;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.UnsupportedCallbackException;

import org.apache.wss4j.common.ext.WSPasswordCallback;

public class UTPasswordCallback implements CallbackHandler {

	// Defining in Memory Database that is used to save userName and passowrd.
	private Map<String, String> pwds= new HashMap<>();
	
	public UTPasswordCallback() {
	// Below are some passwords that be used by Client, for real time we will use Database and LDAP servers
		pwds.put("Kushal", "Kushal");
		pwds.put("Kesarwani", "Kesarwani");
	}
	@Override
	public void handle(Callback[] callbacks) throws IOException, UnsupportedCallbackException {
		
		for (Callback callback : callbacks) {
			WSPasswordCallback passwordCallback = (WSPasswordCallback) callback;
			String password = pwds.get(passwordCallback.getIdentifier());//This will return the userName
			if(null != password)
			{
				passwordCallback.setPassword(password);
				return;
			}
		}

	}

}
