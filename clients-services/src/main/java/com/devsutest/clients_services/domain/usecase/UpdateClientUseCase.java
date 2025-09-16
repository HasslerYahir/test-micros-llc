package com.devsutest.clients_services.domain.usecase;

import com.devsutest.clients_services.application.exceptions.UserExistException;
import com.devsutest.clients_services.application.exceptions.UserNotFoundException;
import com.devsutest.clients_services.domain.gateways.ClientRepository;
import com.devsutest.clients_services.domain.models.Client;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UpdateClientUseCase {
    private final ClientRepository clientRepository;

    public Client updateClient(String identification, String name, String address, String phone) {
        Client client = clientRepository.findByIdentification(identification)
                .orElseThrow(() -> new UserNotFoundException("User not found"));
        Client updateClient = client.toBuilder().name(name)
                .address(address)
                .phone(phone)
                .build();

        return clientRepository
                .update(updateClient);
    }
}
