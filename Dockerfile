FROM openjdk:17-jdk-alpine
ARG JAR-FILE=target/*.jar
COPY ./target/HngStageOne-0.0.1-SNAPSHOT.jar app.jar
#LABEL authors="Chidinma.Afogu"

ENTRYPOINT ["java", "-jar","/app.jar"]