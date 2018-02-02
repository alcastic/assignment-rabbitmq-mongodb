Feature: Home availability
    Scenario: Home Page is accessible
        Given my spring boot app is running
        When client calls "/home"
        Then client resive the message "Be original!"