package com.kushal.restws;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

import org.springframework.stereotype.Service;

import com.kushal.restws.Exception.PatientBusinessException;
import com.kushal.restws.model.Patient;
import com.kushal.restws.model.PatientResponse;

@Service
public class PatientServiceImpl implements PatientService {
	Map<Long, Patient> patients = new HashMap<>();
	long currentID = 123;

	public PatientServiceImpl() {
		init();
	}

	void init() {
		Patient patient = new Patient();
		patient.setId(currentID);
		patient.setName("ABC");
		patients.put(patient.getId(), patient);
	}

	@Override
	public PatientResponse getPatients() {
		Collection<Patient> results = patients.values();
		List<Patient> patientList = new ArrayList<>(results);
		PatientResponse response = new PatientResponse();
		response.setAllPatients(patientList);
		return response;
	}

	@Override
	public Patient getPatient(long id) {
		if (patients.get(id) == null) {
			throw new WebApplicationException(Response.Status.NOT_FOUND);
		}
		return patients.get(id);
	}

	@Override
	public Response createPatient(Patient patient) {
		patient.setId(++currentID);
		patients.put(patient.getId(), patient);
		return Response.ok(patient).build();
	}

	@Override
	public Response updatePatient(Patient patient) {
		Patient currentpatient = patients.get(patient.getId());
		Response response;
		if (null != currentpatient) {
			patients.put(patient.getId(), patient);
			response = Response.ok().build();
		} else {
			throw new PatientBusinessException();
		}
		return response;
	}

	@Override
	public Response deletePatient(long id) {
		Patient currentpatient = patients.get(id);
		Response response;
		if (null != currentpatient) {
			patients.remove(currentpatient.getId());
			response = Response.ok().build();
		} else {
			response = Response.notModified().build();
		}
		return response;
	}

}
