package com.accountservice.handler;

import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;

@Component
public class UserEventHandler {

    @EventHandler
    public void handle() {
        /* TODO */
        System.out.println("This should catch Account create event");
    }
}