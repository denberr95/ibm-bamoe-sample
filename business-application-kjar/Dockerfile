FROM maven:3.9-eclipse-temurin-11 AS builder
WORKDIR /app
COPY src/ src/
COPY pom.xml pom.xml
COPY settings.xml settings.xml
RUN mvn clean deploy --file pom.xml --settings settings.xml
