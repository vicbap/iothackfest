package getronics.qiot.edge_service.service.registration;

/* Main Imports */
import org.jboss.resteasy.annotations.jaxrs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.PUT;
import javax.ws.rs.DELETE;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@Produces(MediaType.TEXT_PLAIN)
@Consumes(MediaType.TEXT_PLAIN)
@Path("/v1")
@RegisterRestClient
public interface RegistrationService {

    /* Registration of the station */
    @PUT
    @Path("/register/serial/{serial}/name/{name}/longitude/{longitude}/latitude/{latitude}")
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.TEXT_PLAIN)
    public Integer regStation(@PathParam("serial") String serial,
    @PathParam("name") String name,
    @PathParam("longitude") Double longitude,
    @PathParam("latitude") Double latitude);

    /* Unregistration of the station */
    @DELETE
    @Path("/register/id/{id}")
    public void unregStation(@PathParam("id") Integer id);

}