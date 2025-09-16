package com.devsutest.clients_services.domain.usecase;

import com.devsutest.clients_services.domain.gateways.ClientRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class DeleteClientUseCase {
    private ClientRepository clientRepository;
    public void delete(String identification){
        clientRepository.delete(identification);
    }
}
