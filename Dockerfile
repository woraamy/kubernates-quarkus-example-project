# Use Maven to build the application
FROM maven:3.8.4-openjdk-11 AS build
COPY src /usr/src/app/src
COPY pom.xml /usr/src/app
RUN mvn -f /usr/src/app/pom.xml clean package

# Use a JRE base image for the final image
FROM openjdk:11-jre-slim
COPY --from=build /usr/src/app/target/*.jar /usr/app/quarkus-app.jar
ENTRYPOINT ["java", "-jar", "/usr/app/quarkus-app.jar"]
