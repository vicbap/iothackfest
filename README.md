# iothackfest

This project consist in develop several pieces of software for providing enviromental data from edge devices to data hub provided by RedHAT

The enviromental data is provided by two devices connected to a Raspberry pi3 Model b.

    https://shop.pimoroni.com/products/enviro?variant=31155658457171
    https://shop.pimoroni.com/products/pms5003-particulate-matter-sensor-with-cable

We use Fedora IoT as base to build every components of the solution.


# podman

To isolate all the pieces we used podman to generate and manage the containers and pods.

We have deployed all the images at quay.io

`docker pull quay.io/cesar_getronics/qiot-sensor-service-base` - Base image whith dependencies to access to the sensors
`docker pull quay.io/cesar_getronics/qiot-sensor-service-collector` - Service that collects data from sensors an call to an http endpoint.
