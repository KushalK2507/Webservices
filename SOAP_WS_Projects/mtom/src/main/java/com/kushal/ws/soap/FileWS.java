package com.kushal.ws.soap;

import javax.activation.DataHandler;
import javax.jws.WebParam;
import javax.jws.WebService;

@WebService
public interface FileWS {
	void upload (@WebParam(name="file")DataHandler attachment);
	DataHandler download();

}
