package com.devsutest.clients_services.infrastructure.entrypoints.rest.data;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class UpdateClientRequest {
    @NotBlank(message = "Address is required")
    @Schema(description = "Client's address", example = "23 Main St", requiredMode = Schema.RequiredMode.REQUIRED)
    private String address;

    @NotBlank(message = "Phone is required")
    @Schema(description = "Phone number", example = "+57 3001234594", requiredMode = Schema.RequiredMode.REQUIRED)
    private String phone;
    @NotBlank(message = "Name is required")
    @Schema(description = "Full name of the client", example = "Jose rick Lema", requiredMode = Schema.RequiredMode.REQUIRED)
    private String name;
}
