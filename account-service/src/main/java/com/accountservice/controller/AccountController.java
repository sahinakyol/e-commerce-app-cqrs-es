package com.accountservice.controller;

import com.accountservice.model.AccountModel;
import com.accountservice.query.GetAccountsQuery;
import lombok.RequiredArgsConstructor;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@RequiredArgsConstructor
public class AccountController {
    private final QueryGateway queryGateway;

    @GetMapping
    public CompletableFuture<List<AccountModel>> getOrders() {
        return queryGateway.query(new GetAccountsQuery(), ResponseTypes.multipleInstancesOf(AccountModel.class));
    }
}
