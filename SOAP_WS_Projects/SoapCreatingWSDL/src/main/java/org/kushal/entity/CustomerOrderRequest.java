package org.kushal.entity;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlType(namespace="www.kushal.org", name="customerOrderRequest")
@XmlAccessorType(XmlAccessType.FIELD)
public class CustomerOrderRequest {

	
	@XmlElement(name="customerdetails")
	private CustomerDetails customerDetails;
	
	@XmlElement(name="customerAddress")
	private CustomerAddress customerAddres;
	
	@XmlElement(name="productDetails")
	private ProductDetails productDetails;

	public CustomerDetails getCustomerDetails() {
		return customerDetails;
	}

	public void setCustomerDetails(CustomerDetails customerDetails) {
		this.customerDetails = customerDetails;
	}

	public CustomerAddress getCustomerAddres() {
		return customerAddres;
	}

	public void setCustomerAddres(CustomerAddress customerAddres) {
		this.customerAddres = customerAddres;
	}

	public ProductDetails getProductDetails() {
		return productDetails;
	}

	public void setProductDetails(ProductDetails productDetails) {
		this.productDetails = productDetails;
	}
}
