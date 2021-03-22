package com.userservice.configuration;

import org.axonframework.messaging.Message;
import org.axonframework.messaging.interceptors.LoggingInterceptor;
import org.axonframework.queryhandling.QueryBus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EventProcessorConfig {

    @Bean
    public LoggingInterceptor<Message<?>> loggingInterceptor() {
        return new LoggingInterceptor<>();
    }

    @Autowired
    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    public void configureLoggingInterceptorFor(QueryBus queryBus, LoggingInterceptor<Message<?>> loggingInterceptor) {
        queryBus.registerDispatchInterceptor(loggingInterceptor);
        queryBus.registerHandlerInterceptor(loggingInterceptor);
    }
}
