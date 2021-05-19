package com.getronics.quarkus;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import com.getronics.quarkus.api.register.RegisterApiClient;
import com.getronics.quarkus.api.register.model.RegisterRequest;
import com.getronics.quarkus.api.register.model.RegisterResponse;
import com.getronics.quarkus.api.sensors.SensorAPIClient;
import com.getronics.quarkus.api.sensors.model.SystemResponse;

import io.netty.handler.codec.mqtt.MqttQoS;
import io.vertx.core.buffer.Buffer;
import io.vertx.mqtt.MqttClient;

@Path("/report")
public class MainResource {

    @RestClient
	@Inject
    RegisterApiClient registerApi;

    @ConfigProperty(name = "qiot.mqtt.client.topic.pollution.topic")
    String keyStorePassword;

    @ConfigProperty(name = "qiot.mqtt.client.topic.pollution.topic")
    String pollutionTopicName;

    @ConfigProperty(name = "qiot.mqtt.client.topic.pollution.qos")
    String pollutionTopicQoS;
    
    @Inject
    MqttClient mqttClient;
    
    @Inject
    @RestClient
	SensorAPIClient sensorAPIClient;


    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
    	
    	SystemResponse systeR = sensorAPIClient.system();
    	
    	String serial = systeR.getId();
    	String name = "";
    	Double latitude = 0.0;
    	Double longitude = 0.0;
    	
    	RegisterRequest registerRequest = new RegisterRequest();
    	registerRequest.setKeyStorePassword(keyStorePassword);
    	registerRequest.setSerial(serial);
    	
		registerRequest.setLatitude(latitude);
    	registerRequest.setLongitude(longitude);
    	registerRequest.setName(name);
    	
		RegisterResponse response = registerApi.register(registerRequest);

    	String data = "";

        mqttClient.publish(pollutionTopicName,Buffer.buffer(data),MqttQoS.valueOf(pollutionTopicQoS),false, false);  
        

        return "Hello RESTEasy";
    }
}