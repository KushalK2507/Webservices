package com.kushal.training;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.SchemaOutputResolver;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.Result;
import javax.xml.transform.stream.StreamResult;

import com.kushal.patient.Patient;


public class JAXBDemo {

	public static void main(String[] args) {
		try {
			JAXBContext context= JAXBContext.newInstance(Patient.class);
			// Marshalliing implementation
			Marshaller marshaller=context.createMarshaller();
			
			Patient patient = new Patient();
			patient.setId(123);
			patient.setName("ABC");
			
			StringWriter writer = new StringWriter();
			marshaller.marshal(patient, writer);
			System.out.println(writer.toString());
			
			//Unmarshalling implementation
			Unmarshaller unMarshaller=context.createUnmarshaller();
			Patient patientResult=(Patient) unMarshaller.unmarshal(new StringReader(writer.toString()));
			
			System.out.println(patientResult.getName());
			System.out.println(patientResult.getId());
			
						
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
 		
	}

}
