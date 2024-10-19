package com.kushal.restws;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;

import org.apache.cxf.feature.Features;

import com.kushal.restws.model.Passenger;

@Features(features = "org.apache.cxf.feature.LoggingFeature")
@Path("/passengerService")
@Produces("application/json")
@Consumes("application/json, application/x-www-form-urlencoded")
public interface PassengerService {

	@Path("/passengers")
	@GET
	public List<Passenger> getPassenger(@QueryParam("start") int start, @QueryParam("size") int size);

	@Path("/passengers")
	@POST
	public void addPassenger(@FormParam("firstName") String firstName, @FormParam("lastName") String lastName,
			@HeaderParam("agent") String agent, @Context HttpHeaders headers);

}
