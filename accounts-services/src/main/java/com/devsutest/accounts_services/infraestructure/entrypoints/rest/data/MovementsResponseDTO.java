package com.devsutest.accounts_services.infraestructure.entrypoints.rest.data;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;
@Getter
@Setter
@NoArgsConstructor
public class MovementsResponseDTO {
    private Instant date;
    private String bankMovements;
    private double amount;
    private double balance;
}
