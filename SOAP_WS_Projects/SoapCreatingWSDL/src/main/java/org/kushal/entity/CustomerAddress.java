package org.kushal.entity;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlType(namespace="www.kushal.org", name="customerAddress", 
propOrder={"houseNumber","building","streetNumber","area","city","state","pinCode","country"})
@XmlAccessorType(XmlAccessType.FIELD)

public class CustomerAddress {

	@XmlElement(name="HouseNumber", required=true)
	private String houseNumber;
	
	@XmlElement(name="Building")
	private String building;
	
	@XmlElement(name="StreetName")
	private String streetNumber;
	
	@XmlElement(name="Locality", required=true)
	private String area;
	
	@XmlElement(name="City", required=true)
	private String city;
	
	@XmlElement(name="State", required=true)
	private String state;
	
	@XmlElement(name="PostCode", required=true)
	private String pinCode;
	
	@XmlElement(name="Country", required=true)
	private String country;
	
	public String getHouseNumber() {
		return houseNumber;
	}
	public void setHouseNumber(String houseNumber) {
		this.houseNumber = houseNumber;
	}
	public String getBuilding() {
		return building;
	}
	public void setBuilding(String building) {
		this.building = building;
	}
	public String getStreetNumber() {
		return streetNumber;
	}
	public void setStreetNumber(String streetNumber) {
		this.streetNumber = streetNumber;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getPinCode() {
		return pinCode;
	}
	public void setPinCode(String pinCode) {
		this.pinCode = pinCode;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	
}
