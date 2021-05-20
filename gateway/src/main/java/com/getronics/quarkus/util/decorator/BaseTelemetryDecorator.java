package com.getronics.quarkus.util.decorator;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;

import javax.inject.Inject;
import javax.inject.Singleton;

import org.slf4j.Logger;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

@Singleton
public class BaseTelemetryDecorator implements TelemetryDecorator {

	private static final String INSTANT = "instant";

	private static final String STATION_ID = "stationId";

	@Inject
	Logger LOGGER;

	@Inject
	ObjectMapper MAPPER;

	@Override
	public String decorate(String stationId, String telemetry) throws JsonProcessingException {

		ObjectNode telemetryNode = (ObjectNode) MAPPER.readTree(telemetry);
		ObjectNode rootNode = MAPPER.createObjectNode();
		rootNode.put(STATION_ID, stationId);
		rootNode.put(INSTANT, OffsetDateTime.now(ZoneOffset.UTC).toInstant().toString());
		rootNode.setAll(telemetryNode);
		return rootNode.toString();
		
	}

}
