package com.devsutest.accounts_services.infraestructure.entrypoints.rest;

import com.devsutest.accounts_services.domain.usecase.CreateAccountMovementUseCase;
import com.devsutest.accounts_services.infraestructure.entrypoints.rest.data.AccountMovementRequest;
import com.devsutest.accounts_services.infraestructure.entrypoints.rest.data.ErrorResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AccountMovementController {
    private final CreateAccountMovementUseCase createAccountMovementUseCase;

    @Operation(summary = "Create an account movement (deposit or withdrawal)")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Movement created successfully"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Account not found",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class))
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid movement request (validation error)",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class))
            ),
            @ApiResponse(
                    responseCode = "409",
                    description = "Insufficient balance",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class))
            )
    })
    @PostMapping("/v1/accounts/movements")
    public ResponseEntity<Void> createAccountMovement(@RequestBody @Valid AccountMovementRequest accountMovementRequest) {
        createAccountMovementUseCase.createAccountMovement(accountMovementRequest.getAccountNumber(), accountMovementRequest.getTypeMovement(), accountMovementRequest.getAmount());
        return ResponseEntity.status(HttpStatus.CREATED)
                .build();
    }
}
