FROM quay.io/cesar_getronics/qiot-sensor-service-base:33-aarch64
RUN dnf install -y  redhat-rpm-config python3-spidev  
# RUN python3 -m pip install st7735 Pillow
COPY collector.py /usr/local/bin/collector.py
CMD ["python3", "/usr/local/bin/collector.py"]
