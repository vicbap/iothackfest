package com.getronics.quarkus.api.sensors;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import com.getronics.quarkus.api.sensors.model.GasResponse;
import com.getronics.quarkus.api.sensors.model.ParticulatesResponse;
import com.getronics.quarkus.api.sensors.model.SystemResponse;

@Path("/")
@RegisterRestClient(configKey = "sensors-api")
public interface SensorAPIClient {
	
	@Path("/system")
	@GET
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	SystemResponse system();
	
	@Path("/gas/all")
	@GET
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	GasResponse gas();
	
	@Path("/particulates/all")
	@GET
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	ParticulatesResponse particulates();
	
	

}
