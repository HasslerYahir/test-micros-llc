package com.devsutest.accounts_services.infraestructure.driveradapter.restconsumer.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ClientDTO {
    private final String identification;
    private final String name;
}
