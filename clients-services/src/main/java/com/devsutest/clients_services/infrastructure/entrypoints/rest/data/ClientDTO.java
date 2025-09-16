package com.devsutest.clients_services.infrastructure.entrypoints.rest.data;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
@Schema(description = "Client response data")
public class ClientDTO {
    @Schema(description = "Unique ID of the client", example = "e2f8c72b-3d64-4a41-9d8f-3e9c5b3d2a11")
    private String id;

    @Schema(description = "Full name of the client", example = "John Doe")
    private String name;

    @Schema(description = "Gender of the client", example = "Male")
    private String gender;

    @Schema(description = "Age of the client", example = "30")
    private int age;

    @Schema(description = "Identification number", example = "1234567890")
    private String identification;

    @Schema(description = "Address of the client", example = "123 Main St")
    private String address;

    @Schema(description = "Phone number", example = "+57 3001234567")
    private String phone;

    @Schema(description = "Status of the client", example = "true")
    private boolean status;
}
