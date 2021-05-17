#!/usr/bin/env python3

import time
from pms5003 import PMS5003, ReadTimeoutError
from bme280 import BME280
from enviroplus import gas
import requests
import json
import os

URL_GATEWAY = os.environ.get('URL_GATEWAY', 'localhost/report/')
WORKSTATION = os.environ.get('WORKSTATION', 'getronics')

try:
    from smbus2 import SMBus
except ImportError:
    from smbus import SMBus

# inicializamos los sensores
pms5003 = PMS5003()
bus = SMBus(1)
bme280 = BME280(i2c_dev=bus)


try:
    while True:
        # leemos los datos
        payload = {}
        payload.workstation = 'WORKSTATION'
        payload.temperature = bme280.get_temperature()
        payload.pressure = bme280.get_pressure()
        payload.humidity = bme280.get_humidity()
        
        payload.gas_adc = gas.read_adc()
        payload.gas_nh3 = gas.read_nh3()
        payload.gas_oxidising = gas.read_oxidising()
        payload.gas_reducing = gas.read_reducing()

        particulates = pms5003.read()
        idata = particulates.data

        payload.pm1_0 = idata[0]
        payload.pm2_5 = idata[1]
        payload.pm10 = idata[2]
        payload.pm1_0_atm = idata[3]
        payload.pm2_5_atm = idata[4]
        payload.pm10_atm = idata[5]
        payload.gt0_3um = idata[6]
        payload.gt0_5um = idata[7]
        payload.gt1_0um = idata[8]
        payload.gt2_5um = idata[9]
        payload.gt5_0um = idata[10]
        payload.gt10um = idata[11]

        resp = resp = requests.post(URL_GATEWAY, data=json.dumps(payload), headers={'Content-Type':'application/json'})
        if resp.status_code != 201:
            print('POST /report/ {}'.format(resp.status_code))

        time.sleep(60)
except KeyboardInterrupt:
    pass

