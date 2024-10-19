package com.kushal.ws.soap.config;


import javax.xml.ws.Binding;
import javax.xml.ws.Endpoint;
import javax.xml.ws.soap.SOAPBinding;

import org.apache.cxf.Bus;
import org.apache.cxf.jaxws.EndpointImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.kushal.ws.soap.FileWSImpl;


@Configuration
public class WebServiceConfig {
	
	@Autowired
	private Bus bus;
	
	@Bean
	public Endpoint endpoint() {
		Endpoint endpoint = new EndpointImpl(bus, new FileWSImpl());
		endpoint.publish("/filews");
		// We used SOAP Binding because this is the only class where setMTOMBinding is defined
		SOAPBinding binding = (SOAPBinding) endpoint.getBinding();
		binding.setMTOMEnabled(true);
		
		return endpoint;
		
	}

}
