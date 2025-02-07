FROM maven:3.9-eclipse-temurin-11 AS builder
WORKDIR /app
COPY src/ src/
COPY pom.xml pom.xml
COPY settings.xml settings.xml
RUN mvn clean package --file pom.xml --settings settings.xml

FROM eclipse-temurin:11-jre-ubi9-minimal AS runtime
WORKDIR /app
COPY --from=builder /app/bin/app.jar app.jar
ENTRYPOINT ["java-entrypoint.sh"]
