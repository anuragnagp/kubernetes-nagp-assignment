FROM openjdk:17-jdk-alpine
COPY api-service/target/api-service-0.0.4.jar api-service-0.0.4.jar
ENTRYPOINT ["java","-jar","/api-service-0.0.4.jar"]