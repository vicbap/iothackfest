package com.getronics.quarkus.service;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.slf4j.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.getronics.quarkus.api.sensors.SensorAPIClient;
import com.getronics.quarkus.domain.StationDataBean;

@ApplicationScoped
public class StationService {
	@Inject
	Logger LOGGER;

	@ConfigProperty(name = "qiot.datafile.path")
	String dataFilePathString;

	@Inject
	ObjectMapper MAPPER;

	@Inject
	@RestClient
	SensorAPIClient sensorApiClient;

	@Inject
	RegistrationService registrationService;

	@ConfigProperty(name = "qiot.station.serial")
	String STATION_SERIAL;
	@ConfigProperty(name = "qiot.station.name")
	String STATION_NAME;
	@ConfigProperty(name = "qiot.station.longitude")
	Double STATION_LONG;
	@ConfigProperty(name = "qiot.station.latitude")
	Double STATION_LAT;

	@ConfigProperty(name = "qiot.mqtts.ks.password")
	String ksPassword;
	@ConfigProperty(name = "qiot.mqtts.ts.password")
	String tsPassword;

	private StationDataBean stationData;

	public StationDataBean checkRegistration() throws Exception {
		Path dataFilePath = Paths.get(dataFilePathString);
		if (Files.exists(dataFilePath)) {
			String datafileContent = Files.readString(dataFilePath);
			stationData = MAPPER.readValue(datafileContent, StationDataBean.class);

		} else {
			stationData = new StationDataBean();
			stationData.setSerial(STATION_SERIAL);
			stationData.setLongitude(STATION_LONG);
			stationData.setLatitude(STATION_LAT);

			try {
				stationData.setSerial(sensorApiClient.getSerialId().getId());
			} catch (Exception e) {
				LOGGER.info("Unable to get serial from pi, using default");
			}

			String stationId = registrationService.register(stationData.getSerial(), stationData.getName(),
					stationData.getLongitude(), stationData.getLatitude(), ksPassword);

			LOGGER.info("Received station ID: {}", stationId);
			stationData.setId(stationId);
			Files.createFile(dataFilePath);

			String stationDataString = MAPPER.writeValueAsString(stationData);
			Files.writeString(dataFilePath, stationDataString);

			LOGGER.info("Data Created successfully: {}", stationData);

		}
		return stationData;
	}

	public String getStationId() {
		return stationData.getId();
	}

	public String getStationSerial() {
		return stationData.getSerial();
	}

	public String getStationName() {
		return stationData.getName();
	}

	public Double getStationLongitude() {
		return stationData.getLongitude();
	}

	public Double getStationLatitude() {
		return stationData.getLatitude();
	}

	public String getTrustStorePassword() {
		return tsPassword;
	}

	public String getKeyStorePassword() {
		return ksPassword;
	}

}
