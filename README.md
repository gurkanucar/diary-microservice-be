# Diary Microservice


### Services

**user microservice:** user operations

**post microservice:** post operations

**notification microservice:** push notification and notification crud operations

**api gateway:** receive the all requests

**eureka server**: allows to comminucate between services withoud hardcoded host and port names


### How to run

#### clone the project: https://gitlab.com/iknow-tech/diary-microservice-be

```bash
  git clone https://gitlab.com/iknow-tech/diary-microservice-be
  cd diary-microservice-be
```

 #### build docker-compose

```bash
  docker-compose build --no-cache
```

 #### run docker-compose

```bash
  docker-compose up --force-recreate
```


#### Sending Postman Requests

You have to send every request to api gateway. Api gateway will work on 8083.

Base URL: http://localhost:8083

Collection: https://gitlab.com/iknow-tech/diary-microservice-be/-/blob/master/diary-microservice-apigateway.postman_collection.json

