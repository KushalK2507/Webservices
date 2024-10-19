package org.kushal.entity;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlType(namespace="www.kushal.org",name="ProductDetails")
@XmlAccessorType(XmlAccessType.FIELD)
public class ProductDetails {

	@XmlElement(name="productName")
	private String productName;
	
	@XmlElement(name="productQuantity")
	private String productQuantity;
	
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getProductQuantity() {
		return productQuantity;
	}
	public void setProductQuantity(String productQuantity) {
		this.productQuantity = productQuantity;
	}
}
