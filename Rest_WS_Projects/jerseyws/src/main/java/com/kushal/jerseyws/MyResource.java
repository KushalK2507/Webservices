package com.kushal.jerseyws;


import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("myresource")
public class MyResource {
	
	@GET
	public String hello()
	{
		return "Hello Jersey";
	}

}
