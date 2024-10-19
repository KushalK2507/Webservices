package com.kushal.restwsclinet.model;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="PatientResponse")
public class PatientResponse {
	
	private List<Patient> patientDetails;

	public List<Patient> getPatientDetails() {
		return patientDetails;
	}

	public void setPatientDetails(List<Patient> patientDetails) {
		this.patientDetails = patientDetails;
	}

}
