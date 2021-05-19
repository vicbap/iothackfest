package com.getronics.quarkus.api.registration;

import com.getronics.quarkus.api.registration.beans.RegisterRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

/**
 * A JAX-RS interface.  An implementation of this interface must be provided.
 */
@Path("/v1")
public interface V1Resource {
  @Path("/register")
  @POST
  @Consumes("application/json")
  void generatedMethod1(RegisterRequest data);
}
