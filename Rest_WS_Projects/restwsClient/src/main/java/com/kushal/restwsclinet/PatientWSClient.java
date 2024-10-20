package com.kushal.restwsclinet;

import com.kushal.restwsclinet.model.Patient;
import com.kushal.restwsclinet.model.PatientResponse;
import jakarta.ws.rs.client.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

public class PatientWSClient {

  private static final String PATIENT_SERVICE_URL =
      "http://localhost:8080/restwsExceptionForClient/services/patientservice";

  public static void main(String[] args) {

    Client client = ClientBuilder.newClient();

    // To get patient details
    // In this now id is variable like we have done on server side.
    // In this we can pass different id for which patient we want to retrieve Data
    WebTarget target =
        client
            .target(PATIENT_SERVICE_URL)
            .path("/patients")
            .path("/{id}")
            .resolveTemplate("id", 123);
    Invocation.Builder request = target.request();
    Patient response = request.get(Patient.class);
    Response responseStatus = request.get();
    System.out.println(response.getId());
    System.out.println("Get patient details = " + response.getName());
    System.out.println("Response Status = " + responseStatus.getStatus());
    responseStatus.close();

    // To update the patient details
    response.setName("XYZ");
    WebTarget putTarget = client.target(PATIENT_SERVICE_URL).path("/patients");
    // Below response is the object of Patient tat we have received using GET
    // method.
    Response updateResponse =
        putTarget.request().put(Entity.entity(response, MediaType.APPLICATION_XML));
    System.out.println("Updated Patient Status " + updateResponse.getStatus());
    updateResponse.close();

    // To create patient
    Patient newPatient = new Patient();
    newPatient.setName("MNO");
    WebTarget postTarget = client.target(PATIENT_SERVICE_URL).path("/patients");
    Patient createResponse =
        postTarget
            .request()
            .post(Entity.entity(newPatient, MediaType.APPLICATION_XML), Patient.class);
    Response createResponseStatus = postTarget.request().get();
    System.out.println("Create Patient Response Status" + createResponseStatus.getStatus());
    System.out.println(
        "Patient Created Successfully with Patient ID: "
            + createResponse.getId()
            + "and Patient Name"
            + createResponse.getName());
    createResponseStatus.close();

    // To get All Patients
    WebTarget getAllTarget = client.target(PATIENT_SERVICE_URL).path("/patients");
    PatientResponse getAllResponse = getAllTarget.request().get(PatientResponse.class);
    for (Patient patient : getAllResponse.getPatientDetails()) {
      System.out.println("Patient details" + patient.getId());
    }

    // To delete the patient
    WebTarget delTarget =
        client
            .target(PATIENT_SERVICE_URL)
            .path("/patients")
            .path("/{id}")
            .resolveTemplate("id", 123);
    Response deleteResponse = delTarget.request().delete();
    System.out.println("Patient Deleted Successfully" + deleteResponse.getStatus());
    deleteResponse.close();
    client.close();
  }
}
