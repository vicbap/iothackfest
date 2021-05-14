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
WEB_PORT = os.environ.get('WEB_PORT', 80)
WEB_HOST = os.environ.get('WEB_HOST', '0.0.0.0'})
STATION_NAME  = os.environ.get('STATION_NAME', 'station'})


def read_def():
    temp = {}
    temp.value = datetime.datetime.utcnow().strftime('%Y-%m-%d %H:%M:%S')
    temp.unit = ''
    temp.tit = STATION_NAME
    return temp


def read_temperature():
    temp = {}
    temp.value = bme280.get_temperature()
    temp.unit = 'ºC'
    temp.tit = 'Temperature'
    return temp


def read_pressure():
    pressure = {}
    pressure.value = bme280.get_pressure()
    pressure.unit = 'hPa'
    pressure.tit = 'Pressure'
    return pressure


def read_humidity():
    humidity = {}
    humidity.value = bme280.get_humidity()
    humidity.unit = '%'
    humidity.tit = 'Humidity'
    return humidity


def read_gas_adc():
    gas = {}
    gas.value = gas.read_adc()
    gas.unit = 'Volt'
    gas.tit = 'Adc'
    return gas


def read_gas_nh3():
    gas = {}
    gas.value = gas.read_nh3()
    gas.unit = 'Ohm'
    gas.tit = 'NH3'
    return gas


def read_gas_oxidising():
    gas = {}
    gas.value = gas.read_oxidising()
    gas.unit = 'Ohm'
    gas.tit = 'Oxidising'
    return gas


def read_gas_reducing():
    gas = {}
    gas.value = gas.read_reducing()
    gas.unit = 'Ohm'
    gas.tit = 'Reducing'
    return gas


def read_particulates():
    particulates = pms5003.read()

    idata = particulates.data
    output = []

    pm1_0 = {tit: 'pm1_0', value: idata[0], unit: ''}
    output.append(pm1_0)
    pm2_5 = {tit: 'pm2_5', value: idata[1], unit: ''}
    output.append(pm2_5)
    pm10 = {tit: 'pm10', value: idata[2], unit: ''}
    output.append(pm10)
    pm1_0_atm = {tit: 'pm1_0_atm', value: idata[3], unit: ''}
    output.append(pm1_0_atm)
    pm2_5_atm = {tit: 'pm2_5_atm', value: idata[4], unit: ''}
    output.append(pm2_5_atm)
    pm10_atm = {tit: 'pm10_atm', value: idata[5], unit: ''}
    output.append(pm10_atm)
    gt0_3um = {tit: 'gt0_3um', value: idata[6], unit: ''}
    output.append(gt0_3um)
    gt0_5um = {tit: 'gt0_5um', value: idata[7], unit: ''}
    output.append(gt0_5um)
    gt1_0um = {tit: 'gt1_0um', value: idata[8], unit: ''}
    output.append(gt1_0um)
    gt2_5um = {tit: 'gt2_5um', value: idata[9], unit: ''}
    output.append(gt2_5um)
    gt5_0um = {tit: 'gt5_0um', value: idata[10], unit: ''}
    output.append(gt5_0um)
    gt10um = {tit: 'gt10um', value: idata[11], unit: ''}
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

    return combined


class Root:
    @cherrypy.tools.json_in()
    def sensors(self, service=None, method=None):
        output = '{}'
        input_json = cherrypy.request.json
        
        if "all" == service:
            out = read_all_sensors_data()
            output = out.to_json(orient='records')
        return output

    sensors.exposed = True

    @cherrypy.tools.json_in()
    def gas(self, service=None, method=None):
        output = '{}'
        input_json = cherrypy.request.json
        
        if "all" == service:
            out = read_all_gas_data()
            output = out.to_json(orient='records')
        return output

    gas.exposed = True


conf = {'/':
        {'tools.staticdir.on': True,
         'tools.staticdir.dir': WEB_ROOT,
         'tools.staticdir.index': 'index.html'}}

cherrypy.config.update({'server.socket_port': WEB_PORT})
cherrypy.config.update({'server.socket_host': WEB_HOST})
cherrypy.quickstart(Root(), '/', conf)