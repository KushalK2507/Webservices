package com.bharath.ws.soap;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

import com.bharath.ws.soap.dto.PaymentProcessorRequest;
import com.bharath.ws.soap.dto.PaymentProcessorResponse;
import com.bharath.ws.soap.exception.ServiceException;

@WebService 
public interface PaymentProcessor {

	@WebMethod
	public @WebResult(name="response")PaymentProcessorResponse processPayment(
			@WebParam(name="paymentProcessorRequest")PaymentProcessorRequest paymentProcessorRequest)
	throws ServiceException; 
}	//the name in the web params can be any which is reflected in the WSDL
