# Situation 

The Red Hat organized in April 2021 Quarkus for IoT Hackfest event allowing 13 partner companies to a development competition.

## Purpose

The objective of this project is to develop a platform for collecting and sending qualitative air data.

For this, the teams had to use Red Hat resources from the Java Framework dedicated to the creation of *Cloud Native* **Quarkus** applications, the Supersonic Subatomic Java Framework.

If you want to learn more about Quarkus, please visit its website: https://quarkus.io/ .

## Environment

To achieve this mission, the teams have all received a development environment:

### Hardware Ressources

- Raspberry PI 3 B+
- Enviro+ Sensors Board 
- PMS5003 - Particulate Matter Sensor

### Software Environment

- Fedora IOT 
- Python and Quarkus

### Class Diagram



## Running the application in reel environment

From Fedora IOT on Raspberry PI, execute the following commands

### Env Variables

```
hostSensor: FQDN or IP of Python Sensor Service ex: (sensor)
nameSensor: Name of Edge Device or Team (default: getronics)
longitudeSensor: Longitude of Device (default: -3.60)
latitudeSensor: Latitude of Device (default: 40.03)
``` 



### Debug Mod

```
podman run \
-it --rm
-v /etc/machine-id:/etc/machine-id:ro \
quay.io/cesar_getronics/edge-service-jvm:1.1
```

## Running the application in dev mode

You can run your application in dev mode that enables live coding using:
```
./mvnw quarkus:dev
```



## Creating a native executable

You can create a native executable using: `./mvnw package -Pnative`.

Or, if you don't have GraalVM installed, you can run the native executable build in a container using: `./mvnw package -Pnative -Dquarkus.native.container-build=true`.

You can then execute your native executable with: `./target/edge-service-1.0.0-SNAPSHOT-runner`

.

## Contributors

Getronics Spain 


