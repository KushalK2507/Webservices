package com.kushal.ws.soap;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;

public class FileWSImpl implements FileWS {

	@Override
	public void upload(DataHandler attachment) {
		InputStream inputStream=null;
		OutputStream outputStream=null;
		try {
			// in this we will read the incoming file into inputStream
			 inputStream = attachment.getInputStream();
			// we will save the incoming file in local machine
			outputStream = new FileOutputStream(new File("G:/myProjects/Projects/mtom/files/upload/test.jpg"));
			 byte [] b=new byte[100000];
			 int byteReads=0;
			 while((byteReads=inputStream.read(b)) != -1)
			 {
				 outputStream.write(b, 0, byteReads);
			 }
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally
		{
			try {
				inputStream.close();
				outputStream.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
	}

	@Override
	public DataHandler download() {
		return new DataHandler(new FileDataSource(new File("G:/myProjects/Projects/mtom/files/download/test.jpg")));
	}

}
