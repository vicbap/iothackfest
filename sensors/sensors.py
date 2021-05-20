# -*- coding: utf-8 -*-
import cherrypy
import datetime
import os
import time
from pms5003 import PMS5003, ReadTimeoutError
from bme280 import BME280
from enviroplus import gas
import json

WEB_ROOT = os.environ.get('WEB_ROOT', '/opt/web/')
WEB_PORT = os.environ.get('WEB_PORT', 8081)
WEB_HOST = os.environ.get('WEB_HOST', '0.0.0.0')
STATION_NAME  = os.environ.get('STATION_NAME', 'station')

try:
    from smbus2 import SMBus
except ImportError:
    from smbus import SMBus
bus = SMBus(1)
bme280 = BME280(i2c_dev=bus)
pms5003 = PMS5003()


def read_def():
    temp = {}
    temp['value'] = 0
    temp['unit'] = datetime.datetime.utcnow().strftime('%Y-%m-%d %H:%M:%S')
    temp['tit'] = STATION_NAME
    return temp


def read_temperature():
    temp = {}
    temp['value'] = bme280.get_temperature()
    temp['unit'] = 'ºC'
    temp['tit'] = 'Temperature'
    return temp


def read_pressure():
    pressure = {}
    pressure['value'] = bme280.get_pressure()
    pressure['unit'] = 'hPa'
    pressure['tit'] = 'Pressure'
    return pressure


def read_humidity():
    humidity = {}
    humidity['value'] = bme280.get_humidity()
    humidity['unit'] = '%'
    humidity['tit'] = 'Humidity'
    return humidity


def read_gas_adc():
    igas = {}
    igas['value'] = gas.read_adc()
    igas['unit'] = 'Volt'
    igas['tit'] = 'Adc'
    return igas


def read_gas_nh3():
    igas = {}
    igas['value'] = gas.read_nh3()
    igas['unit'] = 'Ohm'
    igas['tit'] = 'NH3'
    return igas


def read_gas_oxidising():
    igas = {}
    igas['value'] = gas.read_oxidising()
    igas['unit'] = 'Ohm'
    igas['tit'] = 'Oxidising'
    return igas


def read_gas_reducing():
    igas = {}
    igas['value'] = gas.read_reducing()
    igas['unit'] = 'Ohm'
    igas['tit'] = 'Reducing'
    return igas


def read_particulates():
    particulates = pms5003.read()

    idata = particulates.data
    output = []

    pm1_0 = {'tit': 'pm1_0', 'value': idata[0], 'unit': 'ug/m3'}
    output.append(pm1_0)
    pm2_5 = {'tit': 'pm2_5', 'value': idata[1], 'unit': 'ug/m3'}
    output.append(pm2_5)
    pm10 = {'tit': 'pm10', 'value': idata[2], 'unit': 'ug/m3'}
    output.append(pm10)
    pm1_0_atm = {'tit': 'pm1_0_atm', 'value': idata[3], 'unit': 'ug/m3'}
    output.append(pm1_0_atm)
    pm2_5_atm = {'tit': 'pm2_5_atm', 'value': idata[4], 'unit': 'ug/m3'}
    output.append(pm2_5_atm)
    pm10_atm = {'tit': 'pm10_atm', 'value': idata[5], 'unit': 'ug/m3'}
    output.append(pm10_atm)
    gt0_3um = {'tit': 'gt0_3um', 'value': idata[6], 'unit': 'p/0.1L'}
    output.append(gt0_3um)
    gt0_5um = {'tit': 'gt0_5um', 'value': idata[7], 'unit': 'p/0.1L'}
    output.append(gt0_5um)
    gt1_0um = {'tit': 'gt1_0um', 'value': idata[8], 'unit': 'p/0.1L'}
    output.append(gt1_0um)
    gt2_5um = {'tit': 'gt2_5um', 'value': idata[9], 'unit': 'p/0.1L'}
    output.append(gt2_5um)
    gt5_0um = {'tit': 'gt5_0um', 'value': idata[10], 'unit': 'p/0.1L'}
    output.append(gt5_0um)
    gt10um = {'tit': 'gt10um', 'value': idata[11], 'unit': 'p/0.1L'}
    output.append(gt10um)

   
    
    return output


def read_all_gas_data():
    output = []
    output.append(read_def())
    output.append(read_gas_adc())
    output.append(read_gas_nh3())
    output.append(read_gas_oxidising())
    output.append(read_gas_reducing())

    return output


def read_all_sensors_data():
    output = []
    output.append(read_def())
    output.append(read_temperature())
    output.append(read_pressure())
    output.append(read_humidity())
    output.append(read_gas_adc())
    output.append(read_gas_nh3())
    output.append(read_gas_oxidising())
    output.append(read_gas_reducing())

    p = read_particulates()

    combined = [*output, *p]
    print('combined {}'.format(output))

    return combined


class Root:
    @cherrypy.tools.json_in()
    def sensors(self, service=None, method=None):
        output = '{}'
        input_json = cherrypy.request.json
        
        if "all" == service:
            out = read_all_sensors_data()
            output = json.dumps(out)
        return output

    sensors.exposed = True

    @cherrypy.tools.json_in()
    def gas(self, service=None, method=None):
        output = '{}'
        input_json = cherrypy.request.json
        
        if "all" == service:
            out = read_all_gas_data()
            output = json.dumps(out)
        return output

    gas.exposed = True


conf = {'/':
        {'tools.staticdir.on': True,
         'tools.staticdir.dir': WEB_ROOT,
         'tools.staticdir.index': 'index.html'}}

cherrypy.config.update({'server.socket_port': WEB_PORT})
cherrypy.config.update({'server.socket_host': WEB_HOST})
cherrypy.quickstart(Root(), '/', conf)