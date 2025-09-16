package com.devsutest.accounts_services.infraestructure.entrypoints.rest.data;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@NoArgsConstructor
@Setter
public class AccountMovementRequest {

    @Schema(description = "Account number", example = "100045678")
    @NotNull(message = "accountNumber is required")
    private String accountNumber;

    @Schema(description = "Movement type (DEPOSIT or WITHDRAWAL)", example = "DEPOSIT")
    @NotNull(message = "typeMovement is required")
    private String typeMovement;

    @Schema(description = "Amount of the movement, must be greater than 0", example = "200.50")
    @Min(value = 1, message = "amount must be greater than 0")
    private double amount;
}
