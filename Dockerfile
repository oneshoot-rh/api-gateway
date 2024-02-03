FROM  jre:17-22.04_edge

COPY target/*.jar /one-shoot-gateway.jar

EXPOSE 80:80

ENTRYPOINT ["java","-jar","/one-shoot-gateway.jar"]