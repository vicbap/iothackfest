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
        payload.particulates = pms5003.read()
        payload.gas_data = gas.read_all()

        resp = resp = requests.post(URL_GATEWAY, data=json.dumps(payload), headers={'Content-Type':'application/json'})
        if resp.status_code != 201:
            print('POST /report/ {}'.format(resp.status_code))

        time.sleep(60)
except KeyboardInterrupt:
    pass

