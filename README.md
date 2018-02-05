# ASSIGNMENT

This assignment is a preheating for a production project. It covers SpringBoot, MongoDB and RabbitMQ integration, testing frameworks : Junit with Mockito and Java Cucumber

# Features
To see the features that this project solve [click-here](src/acceptanceTest/resources/features/assignment.feature)

## Spring Modules and Dependencies

* Spring BOOT
* Spring WEB
* Spring Data-Mongodb

## Prerequisites

* Java 8
* Gradle 4.5

## Running the unit tests

```
$ ./gradlew test
```

## Running the acceptance tests

```
$ ./gradlew acceptanceTest
```

## Running the acceptance + unit tests

```
$ ./gradlew clean test acceptanceTest
```

## External Services Configuration
You can easily start external services servers for MongoDB and RabbitMQ through docker containers.
To start docker containers read [int-docker-containers](etc/dependencies.docker.container.start.md)

## Launch REST API

Once MongoDB and RabbitMQ services are running locally with their default configuration execute from a terminal:

```
$ ./gradlew bootRun
```

## Example posting message rest service

Once application is running
```
curl -X POST \
  http://localhost:8080/messages \
  -H 'Cache-Control: no-cache' \
  -H 'Content-Type: text/plain' \
  -H 'Postman-Token: a2df1dc2-f211-c99f-463a-965d45061a8f' \
  -d 'I am Alexanderrr'

```

## Authors

* **Alexander Castillo** - *Initial work*
