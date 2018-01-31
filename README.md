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

## External Services Configuration
You can easily start external services servers for MongoDB and RabbitMQ through docker containers.
To start docker containers read [int-docker-containers](etc/dependencies.docker.container.start.md)

## Launch REST API
Once MongoDB and RabbitMQ services are running locally with their default configuration execute from a terminal:
```
$ ./gradlew bootRun
```

## Running the tests

```
$ ./gradlew test
```

## Authors

* **Alexander Castillo** - *Initial work*
