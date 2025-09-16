package com.devsutest.accounts_services.infraestructure.entrypoints.rest.data;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class AccountRequest {
    @Schema(description = "identification", example = "1254367")
    @NotEmpty(message = "identification is required")
    private String identification;

    @Schema(description = "Type of account (SAVINGS or CURRENT)", example = "SAVINGS")
    @NotEmpty(message = "typeAccount is required")
    private String typeAccount;
}
