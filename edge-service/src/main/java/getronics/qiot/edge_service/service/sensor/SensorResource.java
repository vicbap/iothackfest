package getronics.qiot.edge_service.service.sensor;



import java.time.*;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.inject.Inject;
import java.io.IOException;
import javax.ws.rs.Produces;
import org.jboss.logging.Logger;
import javax.ws.rs.core.MediaType;
import com.fasterxml.jackson.databind.JsonNode;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import getronics.qiot.edge_service.rest.client.registration.AppLifecycleBean;



@Path("/sensor")
public class SensorResource {
    private static final Logger LOGGER = Logger.getLogger("ListenerBean");
    
    @Inject
    @RestClient
    SensorService sensorService;
    
    @Inject
    public AppLifecycleBean app;

    @GET
    @Path("/gas")
    @Produces(MediaType.APPLICATION_JSON)
    public GasResult getGas()  {
        Result res = new Result();
        OffsetDateTime now = OffsetDateTime.now( ZoneOffset.UTC );
        res = sensorService.getGasResult();
        //LOGGER.info(res.toString());
        GasResult sensor = new GasResult((Integer)app.getRegistrationId(),(Double)res.result.get("adc"), now.toString(), (Double) res.result.get("nh3") , ( Double ) res.result.get("oxidising"), (Double)res.result.get("reducing") );
        return sensor;
    }

    @GET
    @Path("/pollution")
    @Produces(MediaType.APPLICATION_JSON)
    public PollutionResult getPollution() {
        Result res = sensorService.getPollutionResult();
        //LOGGER.info(res.toString());
        OffsetDateTime now = OffsetDateTime.now( ZoneOffset.UTC );
        PollutionResult sensor = new PollutionResult( (Integer)app.getRegistrationId(), (Integer)res.result.get("PM10") , (Integer)res.result.get("PM10_atm") , (Integer)res.result.get("PM1_0") ,(Integer)res.result.get("PM1_0_atm") ,(Integer)res.result.get("PM2_5") ,(Integer)res.result.get("PM2_5_atm") ,(Integer) res.result.get("gt0_3um") ,(Integer) res.result.get("gt0_5um") ,(Integer) res.result.get("gt10um") ,(Integer)res.result.get("gt1_0um") ,(Integer)res.result.get("gt2_5um") ,(Integer) res.result.get("gt5_0um") ,  now.toString());
        return sensor;
    }


    public SensorResource() {
    }


}
