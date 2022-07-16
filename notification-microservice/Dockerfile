#
# Build stage
#
FROM maven:3.6.0-jdk-11-slim AS build
COPY src /home/app/src
COPY pom.xml /home/app
RUN mvn -f /home/app/pom.xml clean package -DskipTests

#
# Package stage
#
FROM openjdk:11
COPY --from=build /home/app/target/notification-microservice-0.0.1-SNAPSHOT.jar /usr/local/lib/notification-microservice.jar
EXPOSE 8086
ENTRYPOINT ["java","-jar","/usr/local/lib/notification-microservice.jar"]



