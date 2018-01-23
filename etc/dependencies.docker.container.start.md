To run a docker RabbitMQ container with default port configuration
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

To run a docker MongoDB container with default port configuration
```
$ docker run -d -p 27017:27017 -p 28017:28017 tutum/mongodb
```

