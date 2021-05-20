package com.getronics.quarkus;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.slf4j.Logger;

@Path("/report")
public class MainResource {

	@Inject
	Logger LOGGER;

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String report(String telemetry) {

		LOGGER.info("Received GAS telemetry: {}", telemetry);

		return "Hello RESTEasy";
	}
}