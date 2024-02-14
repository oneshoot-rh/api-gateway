FROM  openjdk:17-jdk-slim

COPY target/*.jar /one-shoot-gateway.jar


ENTRYPOINT ["java","-jar","/one-shoot-gateway.jar"]