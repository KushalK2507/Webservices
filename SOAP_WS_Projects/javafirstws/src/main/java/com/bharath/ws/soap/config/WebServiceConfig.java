package com.bharath.ws.soap.config;

import java.util.HashMap;
import java.util.Map;

import javax.xml.ws.Endpoint;

import org.apache.cxf.Bus;
import org.apache.cxf.jaxws.EndpointImpl;
import org.apache.cxf.ws.security.wss4j.WSS4JInInterceptor;
import org.apache.wss4j.common.ConfigurationConstants;
import org.apache.wss4j.dom.WSConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.bharath.ws.soap.PaymentProcessorImpl;




@Configuration
public class WebServiceConfig {
	
	@Autowired
	private Bus bus;
	
	@Bean
	public Endpoint endpoint() {
		EndpointImpl endpoint = new EndpointImpl(bus, new PaymentProcessorImpl());
		endpoint.publish("/paymentProcessor");
		
		// Below code is used to create the Security in the SOAP Request
		
		Map<String, Object> inProp = new HashMap<>();
		inProp.put(ConfigurationConstants.ACTION, ConfigurationConstants.USERNAME_TOKEN);
		inProp.put(ConfigurationConstants.PASSWORD_TYPE,WSConstants.PW_TEXT);
		inProp.put(ConfigurationConstants.PW_CALLBACK_CLASS, UTPasswordCallback.class.getName());
		//getName() method is from java.lang.class returns the name of the class with "this" object
		WSS4JInInterceptor wssIn = new WSS4JInInterceptor( inProp);
		endpoint.getInInterceptors().add(wssIn);
		return endpoint;
		
	}

}

