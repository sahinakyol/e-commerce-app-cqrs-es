package com.userservice.aggregate;

import com.userservice.command.CreateUserCommand;
import com.userservice.event.AccountCreateCommand;
import com.userservice.event.UserCreatedEvent;
import lombok.NoArgsConstructor;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

import java.math.BigDecimal;
import java.util.UUID;

import static org.axonframework.modelling.command.AggregateLifecycle.apply;

@Aggregate
@NoArgsConstructor
public class User {

    @AggregateIdentifier
    private String id;
    private String name;


    @CommandHandler
    public User(CreateUserCommand command) throws Exception {
        apply(new UserCreatedEvent(
                command.getId(),
                command.getName()
        ));
    }

    @EventSourcingHandler
    public void handle(UserCreatedEvent event) {
        this.id = event.getId();
        this.name = event.getName();
    }
}
