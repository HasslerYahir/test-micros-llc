package com.devsutest.clients_services.infrastructure.entrypoints.rest.data;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Setter
public class RegisterClientRequest {
    @NotBlank(message = "Name is required")
    @Schema(description = "Full name of the client", example = "Jose Lema", requiredMode = Schema.RequiredMode.REQUIRED)
    private String name;

    @NotBlank(message = "Gender is required")
    @Schema(description = "Gender of the client", example = "Male", requiredMode = Schema.RequiredMode.REQUIRED)
    private String gender;

    @Min(value = 1, message = "Age must be greater than 0")
    @Schema(description = "Age of the client (must be > 0)", example = "25", requiredMode = Schema.RequiredMode.REQUIRED)
    private int age;

    @NotBlank(message = "Identification is required")
    @Schema(description = "Identification number (unique)", example = "1254367", requiredMode = Schema.RequiredMode.REQUIRED)
    private String identification;

    @NotBlank(message = "Address is required")
    @Schema(description = "Client's address", example = "123 Main St", requiredMode = Schema.RequiredMode.REQUIRED)
    private String address;

    @NotBlank(message = "Phone is required")
    @Schema(description = "Phone number", example = "+57 3001234567", requiredMode = Schema.RequiredMode.REQUIRED)
    private String phone;

    @NotBlank(message = "Client ID is required")
    @Schema(description = "Client ID assigned", example = "C001", requiredMode = Schema.RequiredMode.REQUIRED)
    private String clientId;

    @NotBlank(message = "Password is required")
    @Schema(description = "Password for login", example = "123456", requiredMode = Schema.RequiredMode.REQUIRED)
    private String password;
}
