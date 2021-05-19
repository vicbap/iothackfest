package getronics.qiot.edge_service.service.sensor;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@Path("/sensors")
@RegisterRestClient
public interface SensorService {
    
    @GET
    @Path("/gas")
    @Produces("application/json")
    Result getGasResult();

    @GET
    @Path("/pollution")
    @Produces("application/json")
    Result getPollutionResult();
}
