package com.devsutest.accounts_services.infraestructure.entrypoints.rest.data;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AccountResponseDTO {
    private String id;
    private String accountNumber;
    private String type;
    private double amount;
    private boolean status;
}
