package com.devsutest.accounts_services.domain.model;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
@Builder
public class Client {
    private final String identification;
    private final String name;
}
