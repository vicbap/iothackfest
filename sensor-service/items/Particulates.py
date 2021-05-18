# -*- coding: utf-8 -*-
from flask import Blueprint
from ReturnValue import return_simple, return_map
particulates_blueprint = Blueprint('particulates', __name__)

import time
from pms5003 import PMS5003, PMS5003Data, ReadTimeoutError

pms5003 = PMS5003()

# pm1.0 ug/m3 (ultrafine particles):
# pm2.5 ug/m3 (combustion particles, organic compounds, metals):
# pm10 ug/m3  (dust, pollen, mould spores):
# pm1.0 ug/m3 (atmos env):
# pm2.5 ug/m3 (atmos env):
# pm10 ug/m3 (atmos env):
# >0.3um in 0.1L air:
# >0.5um in 0.1L air:
# >1.0um in 0.1L air:
# >2.5um in 0.1L air:
# >5.0um in 0.1L air:
# >10um in 0.1L air:
@particulates_blueprint.route("/all")
def all():
    
    psm5003data = pms5003.read()
    readings=psm5003data.data
    
    returnDict = {
        'pm1_0':readings[0],
        'pm2_5':readings[1],
        'pm10':readings[2],
        'pm1_0_atm':readings[3],
        'pm2_5_atm':readings[4],
        'pm10_atm':readings[5],
        'gt0_3um':readings[6],
        'gt0_5um':readings[7],
        'gt1_0um':readings[8],
        'gt2_5um':readings[9],
        'gt5_0um':readings[10],
        'gt10um':readings[11]
    }
    return return_map(returnDict)


# pm1.0 ug/m3 (ultrafine particles)
@particulates_blueprint.route("/pm1_0")
def pm1_0():
    psm5003data = pms5003.read()
    readings=psm5003data.data
    return return_simple(readings[0], "ug/m3")


# pm2.5 ug/m3 (combustion particles, organic compounds, metals)
@particulates_blueprint.route("/pm2_5")
def pm2_5():
    psm5003data = pms5003.read()
    readings=psm5003data.data
    return return_simple(readings[1], "ug/m3")


# pm10 ug/m3  (dust, pollen, mould spores)
@particulates_blueprint.route("/pm10")
def pm10():
    psm5003data = pms5003.read()
    readings=psm5003data.data
    return return_simple(readings[2], "ug/m3")


# pm1.0 ug/m3 (atmos env)
@particulates_blueprint.route("/pm1_0_atm")
def pm1_0_atm():
    psm5003data = pms5003.read()
    readings=psm5003data.data
    return return_simple(readings[3], "ug/m3")


# pm2.5 ug/m3 (atmos env)
@particulates_blueprint.route("/pm2_5_atm")
def pm2_5_atm():
    psm5003data = pms5003.read()
    readings=psm5003data.data
    return return_simple(readings[4], "ug/m3")


# pm10 ug/m3 (atmos env)
@particulates_blueprint.route("/pm10_atm")
def pm10_atm():
    psm5003data = pms5003.read()
    readings=psm5003data.data
    return return_simple(readings[5], "ug/m3")


# >0.3um in 0.1L air:
@particulates_blueprint.route("/gt0_3um")
def gt0_3um():
    psm5003data = pms5003.read()
    readings=psm5003data.data
    return return_simple(readings[6], "um/0.1L")


# >0.5um in 0.1L air:
@particulates_blueprint.route("/gt0_5um")
def gt0_5um():
    psm5003data = pms5003.read()
    readings=psm5003data.data
    return return_simple(readings[7], "um/0.1L")


# >1.0um in 0.1L air:
@particulates_blueprint.route("/gt1_0um")
def gt1_0um():
    psm5003data = pms5003.read()
    readings=psm5003data.data
    return return_simple(readings[8], "um/0.1L")


# >2.5um in 0.1L air:
@particulates_blueprint.route("/gt2_5um")
def gt2_5um():
    psm5003data = pms5003.read()
    readings=psm5003data.data
    return return_simple(readings[9], "um/0.1L")


# >5.0um in 0.1L air:
@particulates_blueprint.route("/gt5_0um")
def gt5_0um():
    psm5003data = pms5003.read()
    readings=psm5003data.data
    return return_simple(readings[10], "um/0.1L")


# >10um in 0.1L air:
@particulates_blueprint.route("/gt10um")
def gt10um():
    psm5003data = pms5003.read()
    readings=psm5003data.data
    return return_simple(readings[11], "um/0.1L")
