package com.bharath.ws.soap;

import org.apache.cxf.feature.Features;

import com.bharath.ws.soap.dto.PaymentProcessorRequest;
import com.bharath.ws.soap.dto.PaymentProcessorResponse;
import com.bharath.ws.soap.exception.ServiceException;

@Features(features = "org.apache.cxf.feature.LoggingFeature")
public class PaymentProcessorImpl implements PaymentProcessor {

	
	public PaymentProcessorResponse processPayment(PaymentProcessorRequest request) throws ServiceException {

		if (request.getCreditCardInfo().getCardNumber() == null
				|| request.getCreditCardInfo().getCardNumber().length() == 0) {
			throw new ServiceException("Invalid Card No");
		}

		PaymentProcessorResponse paymentProcessorResponse = new PaymentProcessorResponse();

		paymentProcessorResponse.setResult(true);
		return paymentProcessorResponse;
	}

}
