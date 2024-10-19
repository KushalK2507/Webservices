package com.kushal.ws.soap;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.cxf.feature.Features;

import com.bharath.ws.trainings.CreateOrdersRequest;
import com.bharath.ws.trainings.CreateOrdersResponse;
import com.bharath.ws.trainings.CustomerOrdersPortType;
import com.bharath.ws.trainings.DeleteOrdersRequest;
import com.bharath.ws.trainings.DeleteOrdersResponse;
import com.bharath.ws.trainings.GetOrdersRequest;
import com.bharath.ws.trainings.GetOrdersResponse;
import com.bharath.ws.trainings.Order;
import com.bharath.ws.trainings.Product;

@Features(features="org.apache.cxf.feature.LoggingFeature")
public class CustomerOrdersWsImpl implements CustomerOrdersPortType {

	HashMap<BigInteger, List<Order>> customerOrders= new HashMap<>();
	int customerID;
	
	public CustomerOrdersWsImpl()
	{
		init();
	}
	public void init()
	{
		List<Order> orders=new ArrayList<>();
		Order order = new Order();
		order.setId(BigInteger.valueOf(1));
		Product product = new Product();
		product.setDescription("IPHONE7");
		product.setId("12345");
		product.setQuantity(BigInteger.valueOf(5));
		order.getProduct().add(product);
		orders.add(order);
		
		customerOrders.put(BigInteger.valueOf(++customerID),orders );
	}
	
	@Override
	public GetOrdersResponse getOrders(GetOrdersRequest request) {
		BigInteger customerId = request.getCustomerId();
		List<Order> orders = customerOrders.get(customerId);
		GetOrdersResponse response = new GetOrdersResponse();
		response.getOrder().addAll(orders);
		return response;
	}

	@Override
	public CreateOrdersResponse createOrders(CreateOrdersRequest request) {
		BigInteger customerId = request.getCustomerId();
		
		Order requestOrder=request.getOrder();//order 
		List <Order> presentOrders=customerOrders.get(customerId);
		
		if(presentOrders== null)
		{
			presentOrders=new ArrayList<>();
			presentOrders.add(requestOrder);
			customerOrders.put(customerId, presentOrders);
		}
		else
		{
			presentOrders.add(requestOrder);
		}
				
		CreateOrdersResponse response = new CreateOrdersResponse();
		response.setResult(true);
		response.setOrder(requestOrder);
		response.setMessage("Added Successfully");
		return response;
	}
	@Override
	public DeleteOrdersResponse deleteOrders(DeleteOrdersRequest request) {
		BigInteger customerId2 = request.getCustomerId();
		List<Order> orders = customerOrders.get(customerId2);
		if(orders == null)
		{
			throw new RuntimeException("Customer does not exist");
		}
		boolean res=false;
		Order or=new Order();
		for(Order order:orders)
		{or=order;
		if(null != order.getId() && order.getId().toString().equals(request.getOrderID())) {
			
			 res=orders.remove(order);
			 break;
		}
		}
		DeleteOrdersResponse response=new DeleteOrdersResponse();
		if(res)
		{
			response.setResult(res);
			response.setOrder(or);
			response.setMessage("Removed Succeesfuuly");
		}
		else
		{
			response.setResult(res);
			response.setOrder(or);
			response.setMessage("Order Does not exist");
		}
		
		return response;
	}

}
