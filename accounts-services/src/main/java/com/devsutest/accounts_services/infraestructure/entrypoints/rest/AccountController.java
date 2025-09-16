package com.devsutest.accounts_services.infraestructure.entrypoints.rest;

import com.devsutest.accounts_services.domain.usecase.CreateAccountUseCase;
import com.devsutest.accounts_services.domain.usecase.GetAccountsByIdentificationUseCase;
import com.devsutest.accounts_services.infraestructure.entrypoints.rest.data.AccountRequest;
import com.devsutest.accounts_services.infraestructure.entrypoints.rest.data.AccountResponseDTO;
import com.devsutest.accounts_services.infraestructure.entrypoints.rest.data.ErrorResponse;
import com.devsutest.accounts_services.infraestructure.entrypoints.rest.mappers.AccountMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class AccountController {
    private final CreateAccountUseCase createAccountUseCase;
    private final GetAccountsByIdentificationUseCase getAccountsByIdentificationUseCase;
    private final AccountMapper accountMapper;

    @Operation(summary = "Create a new account")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Account created successfully"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Client not found",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class))
            ),
            @ApiResponse(
                    responseCode = "409",
                    description = "Account already exists for this client and type",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class))
            ),
            @ApiResponse(
                    responseCode = "409",
                    description = "No available accounts (collision after retries)",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class))
            )
    })
    @PostMapping(value = "/v1/accounts", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> createAccount(@RequestBody AccountRequest accountRequest) {
        createAccountUseCase.createAccount(accountRequest.getTypeAccount(), accountRequest.getIdentification());
        return ResponseEntity.status(
                HttpStatus.CREATED
        ).build();
    }

    @Operation(summary = "Get all accounts for a client by identification")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Accounts found successfully",
                    content = @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = AccountResponseDTO.class)))
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Client not found",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class))
            )
    })
    @GetMapping(value = "/v1/accounts/{identification}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<AccountResponseDTO>> getAllAccountsByIdentification(@PathVariable String identification) {
        return ResponseEntity.ok()
                .body(getAccountsByIdentificationUseCase.execute(identification)
                        .stream().map(this.accountMapper::toDTO)
                        .toList());

    }
}
