# IoT Hackfest

The main goal is developing several pieces of software providing enviromental data from edge devices to data hub provided by RedHAT as part of the [QIoT Project](https://github.com/qiot-project/qiot-project.github.io) under the 2021 Quakus for IoT Hackfest.

The enviromental data is provided by two devices connected to a Raspberry pi3 Model b.

   [Enviro + Air Quality](https://shop.pimoroni.com/products/enviro?variant=31155658457171)

   [PMS5003 Particulate Matter Sensor](https://shop.pimoroni.com/products/pms5003-particulate-matter-sensor-with-cable)
   

Our system should to povide 2 different dataset:

    Gas information
    Pollution


We used [Fedora IoT](https://getfedora.org/en/iot/) as base to build every components of the solution.




### podman ### 

To isolate all the pieces we used [podman](https://podman.io/) to generate and manage the containers and pods.

We have pulished all the images to [quay.io](https://quay.io/) making them available.

`docker pull quay.io/cesar_getronics/qiot-sensor-service-base` - Base image with dependencies to access to the sensors
`docker pull quay.io/cesar_getronics/qiot-sensor-service-collector` - Service that collects data from sensors an call to an http endpoint.
`docker pull quay.io/cesar_getronics/qiot-sensor-service-portal` - Web portal with local data from sensors and and some services exposed.
