package com.devsutest.clients_services.infrastructure.entrypoints.rest;

import com.devsutest.clients_services.domain.models.Client;
import com.devsutest.clients_services.domain.usecase.*;
import com.devsutest.clients_services.infrastructure.entrypoints.rest.data.ClientDTO;
import com.devsutest.clients_services.infrastructure.entrypoints.rest.data.ErrorResponse;
import com.devsutest.clients_services.infrastructure.entrypoints.rest.data.RegisterClientRequest;
import com.devsutest.clients_services.infrastructure.entrypoints.rest.data.UpdateClientRequest;
import com.devsutest.clients_services.infrastructure.entrypoints.rest.mappers.ClientMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ClientController {
    private final RegisterClientUseCase registerClientUseCase;
    private final ClientMapper clientMapper;
    private final GetClientUseCase getClientUseCase;
    private final GetClientFindAllUseCase getClientFindAllUseCase;
    private final UpdateClientUseCase updateClientUseCase;
    private final DeleteClientUseCase deleteClientUseCase;

    @PostMapping(value = "/v1/clients")
    @Operation(summary = "Register a new client")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Client created successfully"
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid input data",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "409",
                    description = "Client already exists",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class)
                    )
            )
    })
    public ResponseEntity<Void> registerClient(@RequestBody @Valid RegisterClientRequest registerClient) {
        Client client = clientMapper.toDomain(registerClient);
        registerClientUseCase.execute(client);
        return ResponseEntity.status(HttpStatus.CREATED)
                .build();
    }


    @Operation(summary = "Get client by identification")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Client found successfully",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ClientDTO.class))
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Client not found",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class))
            )
    })
    @GetMapping(value = "/v1/clients/{identification}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ClientDTO> findByIdentification(@PathVariable String identification) {
        ClientDTO clientDTO = clientMapper.toDTO(getClientUseCase.findByIdentification(identification));
        return ResponseEntity
                .ok(clientDTO);
    }

    @Operation(summary = "Get all clients")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Clients found successfully",
                    content = @Content(
                            mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = ClientDTO.class))
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "No clients found",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class))
            )
    })
    @GetMapping(value = "/v1/clients", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ClientDTO>> getClientAll() {
        List<ClientDTO> clientDTO = getClientFindAllUseCase.getClientAll()
                .stream().map(clientMapper::toDTO)
                .toList();
        return ResponseEntity
                .ok(clientDTO);
    }
    @Operation(summary = "Update client by identification")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Client updated successfully"),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid input data",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class))
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Client not found",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class))
            )
    })
    @PatchMapping(value = "/v1/clients/{identification}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> updateClient(@Valid @RequestBody UpdateClientRequest updateClient, @PathVariable String identification) {
        updateClientUseCase.updateClient(identification, updateClient.getName(), updateClient.getAddress(), updateClient.getPhone());
        return ResponseEntity
                .ok()
                .build();
    }
    @Operation(summary = "Delete client by identification")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "204",
                    description = "Client deleted successfully"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Client not found",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class))
            )
    })
    @DeleteMapping(value = "/v1/clients/{identification}")
    public ResponseEntity<Void> deleteClient(@PathVariable String identification) {
        deleteClientUseCase.delete(identification);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
