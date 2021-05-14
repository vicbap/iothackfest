# -*- coding: utf-8 -*-
import cherrypy
import datetime
import os
import time
from pms5003 import PMS5003, ReadTimeoutError
from bme280 import BME280
from enviroplus import gas
import requests
import json

WEB_ROOT = os.environ.get('WEB_ROOT', '/opt/web/')
WEB_PORT = os.environ.get('WEB_PORT', 80)
WEB_HOST = os.environ.get('WEB_HOST', '0.0.0.0'})


def getGasData():
    gas_data = gas.read_all()
    return gas_data


class Root:
    @cherrypy.tools.json_in()
    def gas(self, service=None, method=None):
        output = '{}'
        input_json = cherrypy.request.json
        
        if "all" == service:
            out = getGasData()
            # output = out.to_json(orient='records')
            # if "details" == method:
                # out = getA(db, input_json)
                # output = out.loc[0].to_json()
        return output

    gas.exposed = True


conf = {'/':
        {'tools.staticdir.on': True,
         'tools.staticdir.dir': WEB_ROOT,
         'tools.staticdir.index': 'index.html'}}

cherrypy.config.update({'server.socket_port': WEB_PORT})
cherrypy.config.update({'server.socket_host': WEB_HOST})
cherrypy.quickstart(Root(), '/', conf)