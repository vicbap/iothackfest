package com.getronics.quarkus.api.registration;

import com.getronics.quarkus.api.registration.beans.RegisterRequest;
import com.getronics.quarkus.api.registration.beans.RegisterResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

/**
 * A JAX-RS interface.  An implementation of this interface must be provided.
 */
@Path("/register")
public interface RegisterResource {
  /**
   * Creates a new instance of a `RegisterRequest`.
   */
  @POST
  @Produces("application/json")
  @Consumes("application/json")
  RegisterResponse createRegisterRequest(RegisterRequest data);

  /**
   * Gets the details of a single instance of a `RegisterRequest`.
   */
  @Path("/{id}")
  @GET
  @Produces("application/json")
  RegisterResponse getRegisterRequest(@PathParam("id") String id);
}
