package com.kushal.ws.soap.client;

import java.io.IOException;

import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.UnsupportedCallbackException;

import org.apache.wss4j.common.ext.WSPasswordCallback;

public class UTCallBackHandlerClient implements CallbackHandler {

	@Override
	public void handle(Callback[] callbacks) throws IOException, UnsupportedCallbackException {
			for(int i=0;i<callbacks.length;i++)
			{
				WSPasswordCallback wspwd=(WSPasswordCallback) callbacks[i];
				if(wspwd.getIdentifier().equals("Kushal"));
				{
					wspwd.setPassword("Kushal");
					return;
				}
			}
	}

}
