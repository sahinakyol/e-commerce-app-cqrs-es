package com.accountservice.handler;

import com.accountservice.event.UserCreatedEvent;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;

@Component
public class UserEventHandler {

    @EventHandler
    public void handle(UserCreatedEvent event) {
        /* TODO */
        System.out.println("This should catch Account create event");
    }
}