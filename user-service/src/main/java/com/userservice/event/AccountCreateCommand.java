package com.userservice.event;

import lombok.Value;

import java.math.BigDecimal;

@Value
public class AccountCreateCommand {
    private String id;
    private String userid;
    private BigDecimal balance;
}
