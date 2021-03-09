package com.userservice.service;

import com.userservice.command.CreateUserCommand;
import com.userservice.dto.UserDto;
import com.userservice.event.DepositMoneyEvent;
import com.userservice.event.WithdrawMoneyEvent;
import com.userservice.dto.DepositDTO;
import com.userservice.dto.WithdrawDTO;
import com.userservice.model.UserModel;
import com.userservice.query.GetUsersQuery;
import lombok.RequiredArgsConstructor;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;


@Service
@RequiredArgsConstructor
public class UserService {
    private final CommandGateway commandGateway;
    private final QueryGateway queryGateway;

    public CompletableFuture<String> depositMoney(DepositDTO transactionDTO) {
        return commandGateway.send(new DepositMoneyEvent(transactionDTO.getUserid(), transactionDTO.getAmount()));
    }

    public CompletableFuture<String> withdrawMoney(WithdrawDTO transactionDTO) {
        return commandGateway.send(new WithdrawMoneyEvent(transactionDTO.getUserid(), transactionDTO.getAmount()));
    }

    public void create(UserDto userDto) {
        CreateUserCommand cmd = new CreateUserCommand(
                UUID.randomUUID().toString(),
                userDto.getName()
        );
        commandGateway.sendAndWait(cmd);
    }

    public CompletableFuture<List<UserModel>> getAll() {
        return queryGateway.query(new GetUsersQuery(), ResponseTypes.multipleInstancesOf(UserModel.class));
    }
}
