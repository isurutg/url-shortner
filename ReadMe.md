# How to Run

## Method 1:
- Go to url shortner service path
+ cd url-shortner-service
- build package
+ mvn clean package -DskipTests
- run service
+ mvn clean package -DskipTests
+ java -Dspring.data.mongodb.uri=${mongoDB_URI} -Dapplication.url=${application_url} -jar /app/server/target/url-shotner-0.0.1-SNAPSHOT.jar

## Method 2
- Go to url shortner service path
+ cd url-shortner-service-# build package
+ mvn clean package -DskipTests
- run using docker 
+ docker-compose up --build


## Available URI's

- create short code
+ POST http://localhost:8080
+ body {
+     longUrl: "http://www.google.com"
+ }
+ response {
+     "status": "SUCCESS",
+     "message": "Short URL creation successful",
+     "result": {
+         "longUrl": "http://www.google.com",
+         "shortUrl": "http://localhost:8081/mjg",
+         "expireDate": "2020-05-09T21:26:47.507+0000"
+     }
+ }


- redirect to long url
+ GET http://localhost:8081/mjg