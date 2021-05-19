package getronics.qiot.edge_service.service.telemetry;



import getronics.qiot.edge_service.service.sensor.Result;
import getronics.qiot.edge_service.service.sensor.GasResult;
import getronics.qiot.edge_service.service.sensor.SensorResource;
import getronics.qiot.edge_service.service.sensor.PollutionResult;


import javax.inject.Inject;
import org.jboss.logging.Logger;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.eclipse.microprofile.rest.client.inject.RestClient;



import io.reactivex.Flowable;
import java.util.concurrent.TimeUnit;
import org.eclipse.microprofile.reactive.messaging.Outgoing;

public class TelemetryService {
    
    private static final Logger LOGGER = Logger.getLogger("ListenerBean");
    @Inject
    SensorResource sr;

    
    /* Gas stream */
    @Outgoing("gas-stream")
    public Flowable <String> streamGasData() throws JsonProcessingException {
        
        /* Creating the ObjectMapper object */
        ObjectMapper mapper = new ObjectMapper();
        LOGGER.info(mapper.writeValueAsString(sr.getGas()));
        return Flowable.interval(5, TimeUnit.SECONDS).map(interval -> mapper.writeValueAsString(sr.getGas()));
    }


    /* Pollution stream */
    @Outgoing("pollution-stream")
    public Flowable <String> streamPollutionData() throws JsonProcessingException {
        /* Creating the ObjectMapper object */
        ObjectMapper mapper = new ObjectMapper();
        LOGGER.info(mapper.writeValueAsString(sr.getPollution()));
        /* Converting the Gas object to JSONString */
        return Flowable.interval(5, TimeUnit.SECONDS).map(interval ->  mapper.writeValueAsString(sr.getPollution()));
    }
}
