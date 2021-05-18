FROM quay.io/cesar_getronics/qiot-sensor-service-base:33-aarch64
RUN pip3 install flask flask_restful
WORKDIR /usr/src/app
COPY start.sh ./
RUN ["chmod", "+x", "/usr/src/app/start.sh"]
COPY items ./items

# Start app
EXPOSE 5000
ENTRYPOINT ["/usr/src/app/start.sh"]
