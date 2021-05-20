package com.getronics.quarkus.service;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.slf4j.Logger;

import com.getronics.quarkus.api.register.RegisterApiClient;
import com.getronics.quarkus.api.register.model.RegisterRequest;
import com.getronics.quarkus.api.register.model.RegisterResponse;

@ApplicationScoped
public class RegistrationService {
    @Inject
    Logger LOGGER;

    @ConfigProperty(name = "qiot.mqtts.ks.path")
    String ksPath;
    @ConfigProperty(name = "qiot.mqtts.ts.path")
    String tsPath;

    @Inject
    @RestClient
    RegisterApiClient registrationClient;

    @Inject
    StationService stationService;

    public String register(String serial, String name, double longitude, double latitude, String ksPassword) throws Exception {
        RegisterRequest registerRequest = null;
        RegisterResponse registerResponse = null;

        registerRequest = new RegisterRequest();
        registerRequest.setSerial(serial);
        registerRequest.setName(name);
        registerRequest.setLongitude(longitude);
        registerRequest.setLatitude(latitude);
        registerRequest.setKeyStorePassword(ksPassword);
        
        registerResponse = registrationClient.register(registerRequest);
        
        String encodedTSString = registerResponse.getTruststore();
        String encodedKSString = registerResponse.getKeystore();

        writeTS(encodedTSString);
        writeKS(encodedKSString);
        
        return registerResponse.getId();
        
    }

    private void writeKS(String encodedKSString) throws IOException {
        byte[] content = Base64.getDecoder()
                .decode(encodedKSString.getBytes(StandardCharsets.UTF_8));
        writeToFile(content, ksPath);
    }

    private void writeTS(String encodedTSString) throws IOException {
        byte[] content = Base64.getDecoder()
                .decode(encodedTSString.getBytes(StandardCharsets.UTF_8));
        writeToFile(content, tsPath);
    }

    private void writeToFile(byte[] content, String destination)
            throws IOException {
        Path file = Paths.get(destination);
        Files.createDirectories(file.getParent());
        Files.createFile(file);
        try (OutputStream outputStream = Files.newOutputStream(file);) {
            outputStream.write(content);
        }
    }

}
