package com.devsutest.accounts_services.domain.model;

import lombok.*;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@With
public class Account {
    private String id;
    private String accountNumber;
    private TypeAccount type;
    private double amount;
    private boolean status;
    private Client client;
}
