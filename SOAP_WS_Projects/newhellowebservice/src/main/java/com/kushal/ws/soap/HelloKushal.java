package com.kushal.ws.soap;

import javax.jws.WebMethod;
import javax.jws.WebService;
import org.apache.cxf.feature.Features;
@WebService
@Features(features="org.apache.cxf.feature.LoggingFeature") // used for logs the request and response
public class HelloKushal {
	@WebMethod
	public int fact()
	{
	int fac=5;
	int result=1;
	while(fac > 1)
	{
		result=result * fac;
		fac--;
	}
	
	return result;
	
	
	}

	
	

}
