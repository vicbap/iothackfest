# -*- coding: utf-8 -*-
from flask import Flask, jsonify, make_response
from Gas import gas_blueprint
from Particulates import particulates_blueprint
from System import system_blueprint

APP = Flask(__name__)

APP.register_blueprint(system_blueprint, url_prefix='/system')
APP.register_blueprint(gas_blueprint, url_prefix='/gas')
APP.register_blueprint(particulates_blueprint, url_prefix='/particulates')

@APP.errorhandler(404)
def not_found(error):
    """ HTTP Error 404 Not Found """
    headers = {}
    return make_response(
        jsonify(
            {
                'error': 'true',
                'msg': str(error)
            }
        ), 404, headers
    )


@APP.errorhandler(405)
def not_allowed(error):
    """ HTTP Error 405 Not Allowed """
    headers = {}
    return make_response(
        jsonify(
            {
                'error': 'true',
                'msg': str(error)
            }
        ), 405, headers
    )


@APP.errorhandler(500)
def internal_error(error):
    """ HTTP Error 500 Internal Server Error """
    headers = {}
    return make_response(
        jsonify(
            {
                'error': 'true',
                'msg': str(error)
            }
        ), 500, headers
    )

# -- This piece of code controls what happens during the HTTP transaction. ---


@APP.before_request
def before_request():
    """ This function handles  HTTP request as it arrives to the API """
    pass


@APP.after_request
def after_request(response):
    """ This function handles HTTP response before send it back to client  """
    return response


@APP.route("/")
def root_route():
    return "IoT hackfest 2021"


if __name__ == "__main__":
    APP.run()
