package org.kushal.config;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.xml.ws.Binding;
import javax.xml.ws.Endpoint;
import javax.xml.ws.handler.Handler;
import javax.xml.ws.soap.SOAPBinding;

import org.apache.cxf.Bus;
import org.apache.cxf.jaxws.EndpointImpl;
import org.apache.cxf.ws.security.wss4j.WSS4JInInterceptor;
import org.apache.wss4j.common.ConfigurationConstants;
import org.apache.wss4j.common.WSS4JConstants;
import org.kushal.handler.CustomerOrderHandler;
import org.kushal.handler.PassHandler;
import org.kushal.service.CustomerOrderServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WebServiceConfig {

	@Autowired
	private Bus bus;

	@Bean
	public Endpoint endpoint() {
		EndpointImpl endpoint = new EndpointImpl(bus, new CustomerOrderServiceImpl());
		endpoint.publish("/customerOrder");

		Map<String, Object> inProps = new HashMap<>();
		inProps.put(ConfigurationConstants.ACTION, ConfigurationConstants.USERNAME_TOKEN);
		inProps.put(ConfigurationConstants.PASSWORD_TYPE, WSS4JConstants.PW_TEXT);
		inProps.put(ConfigurationConstants.PW_CALLBACK_CLASS, PassHandler.class.getName());

		WSS4JInInterceptor interceptor = new WSS4JInInterceptor(inProps);
		endpoint.getInInterceptors().add(interceptor);

		SOAPBinding binding = (SOAPBinding) endpoint.getBinding();
		ArrayList<Handler> handler = new ArrayList<>();
		handler.add(new CustomerOrderHandler());
		binding.setHandlerChain(handler);
		return endpoint;
	}
}
