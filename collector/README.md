# iothackfest collector image

Base image for iot collector.

### Environment variables ###

    URL_GATEWAY = Service URI
    WORKSTATION = Name for the device

### Data exposed ###

    workstation 
    temperature 
    pressure 
    humidity 
        
    gas_adc 
    gas_nh3 
    gas_oxidising 
    gas_reducing 
    pm1_0 
    pm2_5 
    pm10
    pm1_0_atm
    pm2_5_atm
    pm10_atm 
    gt0_3um 
    gt0_5um 
    gt1_0um 
    gt2_5um 
    gt5_0um 
    gt10um 

### podman ####

`quay.io/cesar_getronics/qiot-sensor-service-collector`