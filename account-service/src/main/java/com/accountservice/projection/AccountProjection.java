package com.accountservice.projection;

import com.accountservice.event.AccountCreateEvent;
import com.accountservice.model.AccountModel;
import com.accountservice.query.GetAccountsQuery;
import com.accountservice.repository.AccountProjectionRepository;
import lombok.RequiredArgsConstructor;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.eventhandling.gateway.EventGateway;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class AccountProjection {
    private final AccountProjectionRepository accountProjectionRepository;

    @EventHandler
    public void handle(AccountCreateEvent accountCreateEvent) {
        AccountModel accountModel = new AccountModel(
                accountCreateEvent.getId(),
                accountCreateEvent.getUserid(),
                accountCreateEvent.getBalance()
        );
        accountProjectionRepository.save(accountModel);
    }

    @QueryHandler
    public List<AccountModel> handle(GetAccountsQuery getOrdersQuery) {
        return accountProjectionRepository.findAll();
    }

}
