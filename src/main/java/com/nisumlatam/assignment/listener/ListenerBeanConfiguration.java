package com.nisumlatam.assignment.listener;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ListenerBeanConfiguration {
    @Bean
    public MessageSaveMongoEventListener messageSaveMongoEventListener() {
        return new MessageSaveMongoEventListener();
    }
}
