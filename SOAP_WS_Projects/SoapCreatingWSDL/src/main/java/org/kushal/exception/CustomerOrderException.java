package org.kushal.exception;

import javax.xml.soap.SOAPFault;
import javax.xml.ws.WebFault;
import javax.xml.ws.soap.SOAPFaultException;

@WebFault(name="ServiceException")
public class CustomerOrderException extends SOAPFaultException {
	
	public CustomerOrderException(SOAPFault soapFault) {
		
		super(soapFault);
	}

}
