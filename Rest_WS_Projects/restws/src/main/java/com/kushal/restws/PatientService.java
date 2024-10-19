package com.kushal.restws;

import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

import com.kushal.restws.model.Patient;

@Path("/patientservice")
public interface PatientService {
	
	@Path("/patients")
	@GET
	List<Patient> getPatients();
	
	@Path("/patients/{identifier}")
	@GET
	Patient getPatient(@PathParam(value = "identifier") long id);
	
	@Path("/patients")
	@POST
	Response createPatient(Patient patient);
	
	@Path("/patients")
	@PUT
	Response updatePatient(Patient patient);
	
	@Path("/patients/{deletePatient}")
	@DELETE
	Response  deletePatient(@PathParam(value="deletePatient") long id);
}
