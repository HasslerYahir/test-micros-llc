package com.devsutest.clients_services.domain.usecase;

import com.devsutest.clients_services.application.exceptions.UserExistException;
import com.devsutest.clients_services.domain.gateways.ClientRepository;
import com.devsutest.clients_services.domain.gateways.CredentialRepository;
import com.devsutest.clients_services.domain.models.Client;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class RegisterClientUseCase {
    private final ClientRepository clientRepository;
    private final CredentialRepository credentialRepository;

    public void execute(Client client) {
        clientRepository.findByIdentification(client.getIdentification())
                .ifPresent(c -> {
                    throw new UserExistException(
                            "Client already exists"
                    );
                });
        Client clientWithPass = client.toBuilder()
                .password(credentialRepository.encode(client.getPassword()))
                .status(Boolean.TRUE)
                .build();

        clientRepository.save(clientWithPass);
    }
}
