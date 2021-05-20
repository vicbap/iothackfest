package com.getronics.quarkus.util.decorator;

public interface TelemetryDecorator {
	String decorate(String stationId, String measurement) throws Exception;
}
