package com.devsutest.accounts_services.infraestructure.entrypoints.rest.data;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ErrorResponse {
    private String message;
    private List<String> fieldErrors;
}
