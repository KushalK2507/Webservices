package com.kushal.restws;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javax.ws.rs.core.MediaType;

import org.apache.cxf.jaxrs.client.WebClient;
import org.apache.cxf.jaxrs.ext.multipart.Attachment;
import org.apache.cxf.jaxrs.ext.multipart.ContentDisposition;

public class FileClient {

	public static void main(String[] args) throws FileNotFoundException {

		WebClient client = WebClient.create("http://localhost:8080/restattachemnts/services/fileService/upload");
		// By this we set the client would be sending the attachment and made
		// server side familiar that their will be attachment
		client.type(MediaType.MULTIPART_FORM_DATA);
			
		//which tells the file Name
		ContentDisposition cd = new ContentDisposition("attachments;filename=Kushal_Bike.JPG");
		
		Attachment attachments = new Attachment("root", new FileInputStream(new File("F:/Kushal_Bike.JPG")), cd);

		client.post(attachments);
	}

}
