package com.devsutest.accounts_services.domain.gateways;

import com.devsutest.accounts_services.domain.model.Client;

import java.util.Optional;

public interface ClientGateway {
    Optional<Client> findById(String id);
}
