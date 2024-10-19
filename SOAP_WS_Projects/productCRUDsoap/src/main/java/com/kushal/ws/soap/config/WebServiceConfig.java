package com.kushal.ws.soap.config;

import javax.xml.ws.Endpoint;

import org.apache.cxf.Bus;
import org.apache.cxf.jaxws.EndpointImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.kushal.ws.soap.ProductWS;



@Configuration
public class WebServiceConfig {
	
	@Autowired
	private Bus bus;
	
	@Autowired
	private ProductWS productService;
	
	@Bean
	public Endpoint endpoint() {
		Endpoint endpoint = new EndpointImpl(bus, productService);
		endpoint.publish("/products");
		return endpoint;
		
	}

}
