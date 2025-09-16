package com.devsutest.clients_services.infrastructure.entrypoints.rest.data;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "Error response")
public class ErrorResponse {
    @Schema(description = "General error message", example = "Validation failed")
    private String message;

    @Schema(description = "List of field-specific error messages")
    private List<String> fieldErrors;
}
