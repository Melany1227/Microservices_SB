FROM openjdk:17-jdk-alpine
MAINTAINER baelding.com
COPY target/docker_example-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]