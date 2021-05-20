/**
 * 
 */
package com.getronics.quarkus.service;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;

import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.slf4j.Logger;

import com.getronics.quarkus.api.mqtt.MqttService;
import com.getronics.quarkus.api.sensors.SensorAPIClient;
import com.getronics.quarkus.util.decorator.TelemetryDecorator;

import io.quarkus.runtime.Startup;
import io.quarkus.runtime.StartupEvent;
import io.quarkus.scheduler.Scheduled;

@Startup
@ApplicationScoped
public class EdgeService {

	@Inject
	Logger LOGGER;

	@Inject
	MqttService mqttService;

	@Inject
	StationService stationService;

	@Inject
	@RestClient
	SensorAPIClient sensorApiClient;

	@Inject
	TelemetryDecorator measurementDecorator;

	void onStart(@Observes StartupEvent ev) throws Exception {
		LOGGER.info("The application is starting...{}");
		// stationData =
		stationService.checkRegistration();
		mqttService.getReady();
	}

	@Scheduled(every = "5s", delayed = "5s")
	void gasTelemetry() {

		String enrichedTelemetry = null;
		try {
			String telemetry = sensorApiClient.getGasMeasurement();
			LOGGER.info("Collected GAS telemetry: {}", telemetry);
			enrichedTelemetry = measurementDecorator.decorate(stationService.getStationId(), telemetry);
			LOGGER.info("Enriched GAS telemetry: {}", enrichedTelemetry);
		} catch (Exception e) {
			LOGGER.error("An error occurred collecting GAS telemetry", e);
			return;
		}

		mqttService.sendGas(enrichedTelemetry);

	}

	@Scheduled(every = "5s", delayed = "7s")
	void pollutionTelemetry() {
		String enrichedTelemetry = null;
		try {
			String telemetry = sensorApiClient.getParticulatesMeasurement();
			enrichedTelemetry = measurementDecorator.decorate(stationService.getStationId(), telemetry);
			LOGGER.info("Enriched GAS telemetry: {}", enrichedTelemetry);
		} catch (Exception e) {
			LOGGER.error("An error occurred collecting POLLUTION telemetry ", e);
			return;
		}

		mqttService.sendPollution(enrichedTelemetry);

	}
}
