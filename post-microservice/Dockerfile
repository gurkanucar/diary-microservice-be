FROM openjdk:11
ARG JAR_FILE=target/post-microservice-0.0.1-SNAPSHOT.jar
ADD ${JAR_FILE} post-microservice-0.0.1-SNAPSHOT.jar
EXPOSE 8082
ENTRYPOINT ["java", "-jar" , "post-microservice-0.0.1-SNAPSHOT.jar"]