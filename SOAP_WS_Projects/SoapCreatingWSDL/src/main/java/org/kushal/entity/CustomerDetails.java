package org.kushal.entity;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlType(namespace="www.kushal.org", name="customerDetails")
@XmlAccessorType(XmlAccessType.FIELD)
public class CustomerDetails {
	
	@XmlElement(name="CustomerId")
	private String customerId;
	
	@XmlElement(name="customerName")
	private String customerName;
	
	@XmlElement(name="telephoneNumber")
	private String customerTelephoneNumber;
	
	public String getCustomerId() {
		return customerId;
	}
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getCustomerTelephoneNumber() {
		return customerTelephoneNumber;
	}
	public void setCustomerTelephoneNumber(String customerTelephoneNumber) {
		this.customerTelephoneNumber = customerTelephoneNumber;
	}

}
