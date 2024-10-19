package com.kushal.restwsasync;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;

import com.kushal.restwsasync.model.ChecksList;

@Path(value="/checkProcessorService")
public interface CheckProcessor {

	@POST
	@Path(value="/checks")
	public void processCheck(@Suspended AsyncResponse response,ChecksList checksList);
}
