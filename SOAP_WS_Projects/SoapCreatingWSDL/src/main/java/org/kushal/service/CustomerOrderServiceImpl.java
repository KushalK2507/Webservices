package org.kushal.service;

import javax.jws.WebService;

import org.apache.cxf.feature.Features;
import org.kushal.entity.CustomerAddress;
import org.kushal.entity.CustomerOrderRequest;
import org.kushal.entity.CustomerOrderResponse;
import org.kushal.entity.Header;
import org.kushal.entity.ShipmentDetails;

@WebService
@Features(features="org.apache.cxf.feature.LoggingFeature")
public class CustomerOrderServiceImpl implements CustomerOrderService {

	@Override
	public int checkQuantity(String product) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public CustomerOrderResponse placeOrder(Header header, CustomerOrderRequest request) {

		ShipmentDetails shipOrder = shipOrder(request.getCustomerAddres());
		CustomerOrderResponse response = new CustomerOrderResponse();
		response.setOrderId("1234");
		response.setShipmentDetails(shipOrder);
		return response;
	}

	@Override
	public ShipmentDetails shipOrder(CustomerAddress address) {
		ShipmentDetails shipped = new ShipmentDetails();
		shipped.setTrackingId("1234");
		shipped.setCustomerAddress(address);
		return shipped;
	}

}
