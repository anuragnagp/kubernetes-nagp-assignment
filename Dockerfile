FROM openjdk:17-jdk-alpine
COPY target/api-service-3.0.0.jar api-service-3.0.0.jar
ENTRYPOINT ["java","-jar","/api-service-3.0.0.jar"]