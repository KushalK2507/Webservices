package com.kushal.restws_json;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.core.Response;

import org.springframework.stereotype.Service;

import com.kushal.restws_json.model.Patient;

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
	public List<Patient> getPatients() {
		Collection<Patient> results = patients.values();
		ArrayList<Patient> response = new ArrayList<>(results);
		return response;
	}

	@Override
	public Patient getPatient(long id) {
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
			response = Response.notModified().build();
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
