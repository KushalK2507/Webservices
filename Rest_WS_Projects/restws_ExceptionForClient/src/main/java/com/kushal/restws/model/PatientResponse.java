package com.kushal.restws.model;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "PatientResponse")
public class PatientResponse {
	
	private List<Patient> allPatients;

	public PatientResponse() {

	}

	public List<Patient> getAllPatients() {
		return allPatients;
	}

	public void setAllPatients(List<Patient> allPatients) {
		this.allPatients = allPatients;
	}

}
