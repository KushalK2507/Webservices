package com.kushal.training;

import java.io.File;
import java.io.IOException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.SchemaOutputResolver;
import javax.xml.transform.Result;
import javax.xml.transform.stream.StreamResult;

import com.kushal.patient.Patient;

public class GenerateSceham {

	public static void main(String[] args) {
		
		try {
			JAXBContext context = JAXBContext.newInstance(Patient.class);
//			File file1=new File("creteSchema/ABC");
//			File file=new File(file1, "CreteSchema/xsd");
//			System.out.println(file1);
//			System.out.println(file.getName());
//			System.out.println(file.getPath());
//			System.out.println(file1.getPath());
			
			SchemaOutputResolver sor = new SchemaOutputResolver() {
				
				@Override
				public Result createOutput(String namespaceUri, String suggestedFileName) throws IOException {
					File file=new File(suggestedFileName);
					StreamResult result=new StreamResult(file);
					
					result.setSystemId(file);
					
					
					return result;
				}
			};
			Result res=sor.createOutput( "Patient","xsd/Patient.xsd");
			System.out.println(res.getSystemId());
			context.generateSchema(sor);
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
	}

	}

}
