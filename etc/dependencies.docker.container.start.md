# External Services Servers

Once you have **docker** installed in your machine start the following containers before start **assignment app**

1. To run a docker RabbitMQ container with default port configuration

```
$ docker run -d \
    --name="rabbit1" \
    --hostname="rabbit1"\
    -e RABBITMQ_ERLANG_COOKIE="secret string" \
    -e RABBITMQ_NODENAME="rabbit1" \
    --volume=$(pwd)/rabbitmq.config:/etc/rabbitmq/rabbitmq.config \
    --volume=$(pwd)/definitions.json:/etc/rabbitmq/definitions.json \
    --publish="4369:4369" \
    --publish="5671:5671" \
    --publish="5672:5672" \
    --publish="15671:15671" \
    --publish="15672:15672" \
    --publish="25672:25672" \
    rabbitmq:3-management
```

2. To run a docker MongoDB container with default port configuration

```
$ docker run --name some-mongo -p 27017:27017 -d mongo
```

3. Now you are able to lunch this app, from terminal:

```
./gradlew bootRun
```
