package org.kushal.entity;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlType(namespace = "www.kushal.org", name = "CustomerOrderResponse")
@XmlAccessorType(XmlAccessType.FIELD)
public class CustomerOrderResponse {

	@XmlElement(name = "OrderId")
	private String orderId;

	@XmlElement(name = "shipmentDetails")
	private ShipmentDetails shipmentDetails;

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public ShipmentDetails getShipmentDetails() {
		return shipmentDetails;
	}

	public void setShipmentDetails(ShipmentDetails shipmentDetails) {
		this.shipmentDetails = shipmentDetails;
	}

}
