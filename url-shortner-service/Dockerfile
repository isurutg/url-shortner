FROM maven:3-jdk-11 AS builder

COPY target/*.jar /app.jar

EXPOSE 8080
ENTRYPOINT [ "sh", "-c", "java -Dspring.data.mongodb.uri=mongodb://mongodb:27018 -Dapplication.url=http://localhost:8080/ -jar /app.jar" ]