package com.getronics.quarkus.api.sensors;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import com.getronics.quarkus.api.sensors.model.SystemResponse;

@Path("/")
@RegisterRestClient(configKey = "sensors-api")
public interface SensorAPIClient {

	@GET
	@Path("/system/id")
	@Consumes(MediaType.TEXT_PLAIN)
	@Produces(MediaType.APPLICATION_JSON)
	SystemResponse getSerialId() throws Exception;

	@GET
	@Path("/gas/all")
	@Consumes(MediaType.TEXT_PLAIN)
	@Produces(MediaType.TEXT_PLAIN)
	String getGasMeasurement() throws Exception;

	@GET
	@Path("/particulates/all")
	@Consumes(MediaType.TEXT_PLAIN)
	@Produces(MediaType.TEXT_PLAIN)
	String getParticulatesMeasurement() throws Exception;

}
