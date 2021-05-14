FROM node:alpine as builder
RUN npm install -g gulp bower polymer-cli --unsafe-perm generator-polymer-init-custom-build
WORKDIR /usr/src/app
COPY polymer .
RUN polymer install && polymer build

FROM vicbap/ubuntu_pandas_docker:0.0.0
COPY --from=builder /usr/src/app/build/default /opt/web 
COPY polymer/images/trd-icon.png /opt/web/images/trd-icon.png  
COPY polymer/manifest.json /opt/web/
COPY sensors.py /usr/local/bin/service.py
EXPOSE 80
CMD ["python3",  "/usr/local/bin/service.py" ]