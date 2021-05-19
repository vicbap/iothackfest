package com.getronics.quarkus.api.mqtt;

import java.security.KeyStore;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManagerFactory;

import org.eclipse.microprofile.config.inject.ConfigProperty;

import io.vertx.core.Vertx;
import io.vertx.core.net.JksOptions;
import io.vertx.mqtt.MqttClient;
import io.vertx.mqtt.MqttClientOptions;

public class MqttClientProducer {

  
    @ConfigProperty(name = "qiot.mqtt.client.connection.host")
    String host;
    @ConfigProperty(name = "qiot.mqtt.client.connection.port")
    short port;
    @ConfigProperty(name = "qiot.mqtt.client.connection.ssl")
    boolean enableSsl;
    @ConfigProperty(name = "qiot.mqtt.client.connection.ssl.keystore.location")
    String keystoreLocation;
    @ConfigProperty(name = "qiot.mqtt.client.connection.ssl.keystore.password")
    String keystorePassword;
    @ConfigProperty(name = "qiot.mqtt.client.connection.ssl.truststore.location")
    String truststoreLocation;
    @ConfigProperty(name = "qiot.mqtt.client.connection.ssl.truststore.password")
    String truststorePassword;
    MqttClientOptions options;

    SSLContext sslContext;
    TrustManagerFactory trustManagerFactory;
    KeyStore keyStore;
    
    @Inject
    Vertx vertx;    
    private MqttClient client;

    @PostConstruct
    void init() throws Exception {
        options = new MqttClientOptions();
        options.setReconnectAttempts(-1);
        options.setCleanSession(true);
        options.setAutoKeepAlive(true);
        options.setAutoGeneratedClientId(true);
        options.setSsl(enableSsl);
        options.setKeyCertOptions(getKeyCertOptions());
        options.setTrustOptions(getTrustOptions());
        options.setMaxInflightQueue(65535);
        client = MqttClient.create(vertx, options);
    }

    private JksOptions getKeyCertOptions() {
        JksOptions returnJksOptions = new JksOptions().setPath(keystoreLocation).setPassword(keystorePassword);
        return returnJksOptions;
    }

    private JksOptions getTrustOptions() {
        JksOptions returnJksOptions = new JksOptions().setPath(truststoreLocation).setPassword(truststorePassword);
        return returnJksOptions;
    }

    @Produces
    public MqttClient produceMqttClient() {
        if (!client.isConnected()) {
            connectClient();
        }
        return client;
    }

    private void connectClient() {
        client.connect(port, host);
    }
    
    @PreDestroy
    void destroy() {
        if (client != null && client.isConnected())client.disconnect();
    }
}
