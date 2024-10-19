package com.kushal.ws.soap;

import java.math.BigInteger;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import com.bharath.ws.trainings.CreateOrdersRequest;
import com.bharath.ws.trainings.CreateOrdersResponse;
import com.bharath.ws.trainings.CustomerOrdersPortType;
import com.bharath.ws.trainings.GetOrdersRequest;
import com.bharath.ws.trainings.GetOrdersResponse;
import com.bharath.ws.trainings.Order;
import com.bharath.ws.trainings.Product;

public class CustomerOrderWsClient {

	public static void main(String[] args) throws MalformedURLException {
		
		CustomerOrdersWsImplService service = new CustomerOrdersWsImplService(new URL("http://localhost:8080/wsdlfirstws/customersorderservice?wsdl"));
		CustomerOrdersPortType wsImplPort = service.getCustomerOrdersWsImplPort();
		
		// To get Order (getOrders)
		
		GetOrdersRequest request = new GetOrdersRequest();
		request.setCustomerId(BigInteger.valueOf(1));
		GetOrdersResponse response = wsImplPort.getOrders(request);
			List<Order> order = response.getOrder();
			System.out.println(order.get(0));
			
		//To create order (createOrders)
		CreateOrdersRequest createOrderRequest = new CreateOrdersRequest();
		createOrderRequest.setCustomerId(BigInteger.valueOf(2));
		Order newOrder = new Order();
		newOrder.setId(BigInteger.valueOf(432));
		Product e = new Product();
		e.setDescription("MAC");
		e.setId("1221");
		e.setQuantity(BigInteger.TEN);
		newOrder.getProduct().add(e);
		createOrderRequest.setOrder(newOrder);
		CreateOrdersResponse createOrdersResponse = wsImplPort.createOrders(createOrderRequest);
		System.out.println(createOrdersResponse.getOrder().getId());
		System.out.println(createOrdersResponse.getMessage());
		
	}

}
