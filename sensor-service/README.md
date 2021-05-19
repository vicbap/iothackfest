# IoT Hackfest sensor service

Fork from [qiot-covid19-edge-sensors](https://github.com/qiot-project/qiot-covid19-edge-sensors)

Exposes Gas and particulates data over API.

### endpoints ####

/system/id

/gas/all
/gas/oxidising
/gas/reducing
/gas/nh3
/gas/adc

/particulates/all
/particulates/pm1_0
/particulates/pm2_5
/particulates/pm10
/particulates/pm1_0_atm
/particulates/pm2_5_atm
/particulates/pm10_atm
/particulates/gt0_3um
/particulates/gt0_5um
/particulates/gt1_0um
/particulates/gt2_5um
/particulates/gt5_0um
/particulates/gt10um


### Data exposed ###

    workstation     

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

Build command

    sudo podman build -t quay.io/cesar_getronics/qiot-sensor-service:33-aarch64 .

Image availabe at [quay.io](https://quay.io/repository/cesar_getronics/qiot-sensor-service)