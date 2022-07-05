FROM openjdk:11
ARG JAR_FILE=target/user-microservice-0.0.1-SNAPSHOT.jar
ADD ${JAR_FILE} user-microservice-0.0.1-SNAPSHOT.jar
EXPOSE 8081
ENTRYPOINT ["java", "-jar" , "user-microservice-0.0.1-SNAPSHOT.jar"]
