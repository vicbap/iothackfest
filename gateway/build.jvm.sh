chmod +x mvn*
mvn -N io.takari:maven:wrapper
mvn clean package
# -Pnative -Dquarkus.native.container-build=true -Dquarkus.native.container-runtime=docker
docker rmi quay.io/cesar_getronics/qiot-edge-service:2.0.0-aarch64-jvm
docker build -f src/main/docker/Dockerfile.jvm -t quay.io/cesar_getronics/qiot-edge-service:2.0.0-aarch64-jvm .
docker push quay.io/cesar_getronics/qiot-edge-service:2.0.0-aarch64-jvm


