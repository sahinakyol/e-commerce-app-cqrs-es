package com.accountservice.aggregate;

import com.accountservice.command.AccountCreateCommand;
import com.accountservice.command.UserCreateCommand;
import com.accountservice.event.AccountCreateEvent;
import com.accountservice.event.DepositMoneyEvent;
import com.accountservice.event.WithdrawMoneyEvent;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;

import java.math.BigDecimal;

import static org.axonframework.modelling.command.AggregateLifecycle.apply;

@Aggregate
public class Account {
    @AggregateIdentifier
    private String id;
    private String userid;
    private BigDecimal balance;

    @CommandHandler
    public void handle(AccountCreateCommand command) {
            apply(new AccountCreateEvent(
                    command.getId(),
                    command.getUserid(),
                    command.getBalance()
            ));
    }


    @EventSourcingHandler
    protected void createAccount(AccountCreateEvent event) {
        this.id = event.getId();
        this.userid = event.getUserid();
        this.balance = event.getBalance();
    }

    @EventSourcingHandler
    protected void depositMoney(DepositMoneyEvent event) {
        if (BigDecimal.ZERO.compareTo(event.getAmount()) <= 0) {
            throw new IllegalStateException();
        }

        this.balance = this.balance.add(event.getAmount());
    }

    @EventSourcingHandler
    protected void withdrawMoney(WithdrawMoneyEvent event) {
        if (this.balance.subtract(event.getAmount()).compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalStateException();
        } else if (event.getAmount().compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalStateException();
        }

        this.balance = this.balance.subtract(event.getAmount());
    }

    @EventSourcingHandler
    public void handler(UserCreateCommand event) {
        System.out.println("");
    }
}
