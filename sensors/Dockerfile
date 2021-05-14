FROM node:alpine as builder
RUN npm install -g gulp bower polymer-cli --unsafe-perm generator-polymer-init-custom-build
WORKDIR /usr/src/app
COPY polymer .
RUN polymer install && polymer build

FROM vicbap/iothf-base:0.0.0


#Install python dependencies with pip3
WORKDIR /usr/src/app
COPY requirements.txt ./
RUN pip3 install --no-cache-dir -r requirements.txt


COPY --from=builder /usr/src/app/build/default /opt/web 
COPY polymer/images/trd-icon.png /opt/web/images/trd-icon.png  
COPY polymer/manifest.json /opt/web/
COPY sensors.py /usr/local/bin/service.py
EXPOSE 80
CMD ["python3",  "/usr/local/bin/service.py" ]