package com.devsutest.clients_services.domain.usecase;

import com.devsutest.clients_services.application.exceptions.UserNotFoundException;
import com.devsutest.clients_services.domain.gateways.ClientRepository;
import com.devsutest.clients_services.domain.models.Client;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class GetClientUseCase {
    private final ClientRepository clientRepository;

    public Client findByIdentification(String id) {
        return clientRepository.findByIdentification(id)
                .orElseThrow(() -> new UserNotFoundException("User not found"));
    }
}
