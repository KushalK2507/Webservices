package com.kushal.restws.Exception;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

public class PassengerExceptionMapper implements ExceptionMapper<PassengerException> {

	@Override
	public Response toResponse(PassengerException exception) {
		StringBuilder sb = new StringBuilder();
		sb.append("{");
		sb.append("\"status\":\"error\"");
		sb.append(",");
		sb.append("\"message\":\"Try Again later\"");
		sb.append("}");
		return Response.serverError().entity(sb.toString()).type(MediaType.APPLICATION_JSON).build();
	}

}
