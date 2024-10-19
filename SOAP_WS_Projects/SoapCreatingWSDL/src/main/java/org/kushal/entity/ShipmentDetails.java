package org.kushal.entity;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlType(namespace = "www.kushal.org", name = "shipmentDetails")
@XmlAccessorType(XmlAccessType.FIELD)
public class ShipmentDetails {

	
	@XmlElement(name = "customerAddress")
	private CustomerAddress customerAddress;

	@XmlElement(name = "trackingId")
	private String trackingId;

	public CustomerAddress getCustomerAddress() {
		return customerAddress;
	}

	public void setCustomerAddress(CustomerAddress customerAddress) {
		this.customerAddress = customerAddress;
	}

	public String getTrackingId() {
		return trackingId;
	}

	public void setTrackingId(String trackingId) {
		this.trackingId = trackingId;
	}
}
