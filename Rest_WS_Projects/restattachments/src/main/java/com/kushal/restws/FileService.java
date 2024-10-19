package com.kushal.restws;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import org.apache.cxf.jaxrs.ext.multipart.Attachment;
import org.springframework.stereotype.Service;

@Path("/fileService")
@Service
public class FileService {

	private static final String FILE_PATH = "G:/Upload/";

	@POST
	@Path("/upload")
	public void upload(List<Attachment> attachments) throws FileNotFoundException, IOException
	{
		for(Attachment attachment:attachments)
		{
			MultivaluedMap<String, String> multivaluedMap = attachment.getHeaders();
            String fileName = getFileName(multivaluedMap);
			
			copyFile(attachment.getDataHandler().getInputStream(), fileName);
		}
	}
	
	@GET
	@Path("/downnload")
	public Response download() {
		File file = new File("G:/Upload/Kushal_Bike.JPG");
		ResponseBuilder responseBuilder = Response.ok(file);
		responseBuilder.header("content-Disposition", "attachments;fileName=downloaded.JPG" );
		return responseBuilder.build();
	}
	
	
	private String getFileName(MultivaluedMap<String, String> multivaluedMap) {
		 
        String[] contentDisposition = multivaluedMap.getFirst("Content-Disposition").split(";");
        for (String filename : contentDisposition) {
 
            if ((filename.trim().startsWith("filename"))) {
                String[] name = filename.split("=");
                String exactFileName = name[1].trim().replaceAll("\"", "");
                return exactFileName;
            }
        }
        return "unknownFile";
    }
	private void copyFile(InputStream inputStream, String nameFile) throws FileNotFoundException, IOException {
		OutputStream out = null;
		int read = 0;
		byte[] bytes = new byte[1024];
		File file = new File (FILE_PATH + nameFile);
		out = new FileOutputStream(file);
		while ((read = inputStream.read(bytes)) != -1) {
			out.write(bytes, 0, read);
		}
		out.flush();
		out.close();	
	}

}