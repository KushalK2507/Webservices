package org.kushal.handler;

import java.io.IOException;

import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.UnsupportedCallbackException;
import org.apache.wss4j.common.ext.WSPasswordCallback;

public class PassHandler implements CallbackHandler {

	@Override
	public void handle(Callback[] callbacks) throws IOException, UnsupportedCallbackException {
		for (Callback callback :callbacks)
		{
			WSPasswordCallback passwordCallBack = (WSPasswordCallback) callback;
			String password = passwordCallBack.getIdentifier();
			passwordCallBack.setPassword(password);
					
		}

	}

}
