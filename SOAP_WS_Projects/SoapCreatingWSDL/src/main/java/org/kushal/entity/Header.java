package org.kushal.entity;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlType(namespace="www.kushal.org", name="header")
@XmlAccessorType(XmlAccessType.FIELD)
public class Header {
	
	@XmlElement(name="userLogin")
	private String userLogin;

	public String getUserLogin() {
		return userLogin;
	}

	public void setUserLogin(String userLogin) {
		this.userLogin = userLogin;
	}

}
