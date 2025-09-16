package com.devsutest.accounts_services.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.Instant;
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AccountMovements {
    private String id;
    private Instant date;
    private TypeMovement typeMovement;
    private double amount;
    private double balance;
}
