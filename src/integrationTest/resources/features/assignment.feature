Feature: Assignment
    Scenario: Post a message to rabbitmq exchange, listen and save to mongodb
        Given i have a message to post
        And rabbitmq in-queue is configured
        And listener is up and running
        And my spring boot app is up and running
        When I trigger a post endpoint
        Then I should see message posted to rabbitmq in-queue
        And I should see message saved to mongodb

    Scenario: Listen mongo event save, post back to out-queue
        Given i have a mongo event listener configured
        And rabbitmq in-queue is configured
        And my spring boot app is up and running
        When message saved into mongo
        Then I should see mongo listener picks the message
        And post to rabbitmq out-queue