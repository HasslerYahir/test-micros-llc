package com.devsutest.accounts_services.infraestructure.entrypoints.rest;

import com.devsutest.accounts_services.domain.usecase.GetMovementsAccountsUseCase;
import com.devsutest.accounts_services.infraestructure.driveradapter.jparepositorys.mappers.AccountMovementsMapper;
import com.devsutest.accounts_services.infraestructure.entrypoints.rest.data.ErrorResponse;
import com.devsutest.accounts_services.infraestructure.entrypoints.rest.data.MovementsResponseDTO;
import com.devsutest.accounts_services.infraestructure.entrypoints.rest.mappers.MovementsMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class ReportAccountController {
    private final GetMovementsAccountsUseCase getMovementsAccountsUseCase;
    private final MovementsMapper movementsMapper;

    @Operation(
            summary = "Get account movements report",
            description = "Returns a list of movements for the given client identification and date range"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "List of movements retrieved successfully",
                    content = @Content(
                            mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = MovementsResponseDTO.class))
                    )
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid date range (e.g., start date is after end date)",
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
    @GetMapping("/v1/reports/movements")
    public ResponseEntity<List<MovementsResponseDTO>> getMovementsAccounts(
            @Parameter(description = "Client identification", required = true)
            @RequestParam String identification,

            @Parameter(description = "Start date (inclusive)", required = true,
                    example = "2025-09-01T00:00:00Z")
            @RequestParam Instant start,

            @Parameter(description = "End date (inclusive)", required = true,
                    example = "2025-09-14T23:59:59Z")
            @RequestParam Instant end){
        return ResponseEntity
                .ok(getMovementsAccountsUseCase.getMovementsAccounts(start,end,identification)
                        .stream().map(this.movementsMapper::toDTO).toList());
    }
}
