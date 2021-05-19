package com.getronics.quarkus.api.register;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import com.getronics.quarkus.api.register.model.RegisterRequest;
import com.getronics.quarkus.api.register.model.RegisterResponse;

@Path("/v1/register")
@RegisterRestClient(configKey = "registration-api")
public interface RegisterApiClient {
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	RegisterResponse register(RegisterRequest data);
}
