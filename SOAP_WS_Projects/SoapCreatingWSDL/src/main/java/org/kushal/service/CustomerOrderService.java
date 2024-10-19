package org.kushal.service;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.ws.WebFault;

import org.apache.cxf.feature.Features;
import org.kushal.entity.CustomerAddress;
import org.kushal.entity.CustomerOrderRequest;
import org.kushal.entity.CustomerOrderResponse;
import org.kushal.entity.Header;
import org.kushal.entity.ShipmentDetails;
import org.kushal.exception.CustomerOrderException;

@WebService
@Features(features="org.apache.cxf.feature.LoggingFeature")
public interface CustomerOrderService {

	@WebMethod
	@WebResult(name="quantity")int checkQuantity(@WebParam(name="productName")String product);
	
	@WebMethod
	@WebResult(name="CustomerOrderResponse")CustomerOrderResponse placeOrder(@WebParam(name="kushalHeader", header=true)Header header,@WebParam(name="customerOrderRequest")CustomerOrderRequest request) throws CustomerOrderException;
	
	@WebMethod
	@WebResult(name="shipmentDetails")ShipmentDetails shipOrder(@WebParam(name="customerAddress")CustomerAddress address);
	
	
	
}
