FROM vicbap/iothf-base:0.0.0
RUN dnf install -y python3-setuptools python3-devel redhat-rpm-config python3-RPi.GPIO python3-spidev python3-pip python3-numpy
RUN python3 -m pip install st7735 enviroplus Pillow
COPY collector.py /usr/local/bin/collector.py
CMD ["python3", "/usr/local/bin/collector.py"]
