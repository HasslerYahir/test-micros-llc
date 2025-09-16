package com.devsutest.clients_services.domain.gateways;

import com.devsutest.clients_services.domain.models.Client;

import java.util.List;
import java.util.Optional;

public interface ClientRepository {
    Client save(Client client);
    Optional<Client> findByIdentification(String clientId);
    List<Client> findAll();
    Client update(Client client);
    void delete(String identification);
}
