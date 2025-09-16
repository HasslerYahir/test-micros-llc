package com.devsutest.clients_services.domain.usecase;

import com.devsutest.clients_services.domain.gateways.ClientRepository;
import com.devsutest.clients_services.domain.models.Client;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class GetClientFindAllUseCase {
    private final ClientRepository clientRepository;
    public List<Client> getClientAll(){
        return clientRepository.findAll();
    }
}
