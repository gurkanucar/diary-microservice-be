FROM openjdk:11
ARG JAR_FILE=target/api-gateway-0.0.1-SNAPSHOT.jar
ADD ${JAR_FILE} api-gateway-0.0.1-SNAPSHOT.jar
EXPOSE 8083
ENTRYPOINT ["java", "-jar" , "api-gateway-0.0.1-SNAPSHOT.jar"]
