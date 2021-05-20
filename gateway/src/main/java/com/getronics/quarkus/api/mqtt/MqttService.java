package com.getronics.quarkus.api.mqtt;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.slf4j.Logger;

import io.netty.handler.codec.mqtt.MqttQoS;
import io.vertx.mqtt.MqttException;
import io.vertx.mutiny.core.buffer.Buffer;
import io.vertx.mutiny.mqtt.MqttClient;

@ApplicationScoped
public class MqttService {

	@Inject
	private MqttClient mqttClient;

	@Inject
	Logger LOGGER;

	/** Gas values **/
	@ConfigProperty(name = "qiot.mqtt.client.topic.gas.topic")
	String gasTopicName;
	@ConfigProperty(name = "qiot.mqtt.client.topic.gas.qos")
	int gasTopicQoS;

	/** Pollution values **/
	@ConfigProperty(name = "qiot.mqtt.client.topic.pollution.topic")
	String pollutionTopicName;
	@ConfigProperty(name = "qiot.mqtt.client.topic.pollution.qos")
	int pollutionTopicQoS;

	@PostConstruct
	void init() throws Exception {
		Map<String, Integer> topicsMap = new HashMap<>();
		topicsMap.put(gasTopicName, gasTopicQoS);
		topicsMap.put(pollutionTopicName, pollutionTopicQoS);
		mqttClient.subscribeAndAwait(topicsMap);
		LOGGER.info("Subscribed to target topics.");
	}

	public void getReady() {

	}

	/**
	 * Send gas.
	 *
	 * @param data the data
	 * @throws MqttException
	 * @throws MqttPersistenceException
	 */
	public void sendGas(String data) {
		LOGGER.info("Sending out GAS measurement");	
		sendTopic(gasTopicName, gasTopicQoS,  data);
	}

	/**
	 * Send pollution.
	 *
	 * @param data the data
	 * @throws MqttPersistenceException, MqttException
	 */
	public void sendPollution(String data) {
		LOGGER.info("Sending out POLLUTION measurement");		
		sendTopic(pollutionTopicName, pollutionTopicQoS,  data);
	}

	/**
	 * Send topic.
	 *
	 * @param topic the topic
	 *
	 * @param qos   the QoS
	 *
	 * @param data  the data
	 * @throws MqttPersistenceException, MqttException
	 */
	public void sendTopic(String topic, int qos, String data) {
		LOGGER.info("Sending out " + topic + "with " + qos);
		mqttClient.publishAndAwait(topic, Buffer.buffer(data), MqttQoS.valueOf(qos), false, false);
	}

}
