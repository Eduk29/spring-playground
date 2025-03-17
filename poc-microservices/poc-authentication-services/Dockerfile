# the first stage of our build will use a maven 3.6.1 parent image
FROM maven:3.6.1-jdk-17-alpine AS MAVEN_BUILD

# copy the pom and src code to the container
COPY ./ ./

# package our application code
RUN mvn clean package

# the second stage of our build will use open jdk 17 on alpine
FROM eclipse-temurin:17-jdk-alpine

ARG JAR_FILE=target/poc-authentication-services-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} /poc-authentication-services.jar

# set the startup command to execute the jar
CMD ["java","-jar","/poc-authentication-services.jar"]