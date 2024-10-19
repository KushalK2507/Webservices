package com.kushal.ws.soap.config;

import java.util.ArrayList;
import java.util.List;

import javax.xml.ws.Endpoint;
import javax.xml.ws.handler.Handler;
import javax.xml.ws.soap.SOAPBinding;

import org.apache.cxf.Bus;
import org.apache.cxf.jaxws.EndpointImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.kushal.ws.handler.SiteHandler;
import com.kushal.ws.soap.CustomerOrdersWsImpl;


@Configuration
public class WebServiceConfig {
	
	@Autowired
	private Bus bus;
	
	@Bean
	public Endpoint endpoint() {
		Endpoint endpoint = new EndpointImpl(bus, new CustomerOrdersWsImpl());
		endpoint.publish("/customersorderservice");
		SOAPBinding binding = (SOAPBinding) endpoint.getBinding();
		List<Handler> handlerChain = new ArrayList<>();
		handlerChain.add(new SiteHandler());
		binding.setHandlerChain(handlerChain );
		return endpoint;
		
	}

}
