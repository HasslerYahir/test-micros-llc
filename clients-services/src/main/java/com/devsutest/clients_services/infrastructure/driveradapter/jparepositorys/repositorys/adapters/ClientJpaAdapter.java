package com.devsutest.clients_services.infrastructure.driveradapter.jparepositorys.repositorys.adapters;

import com.devsutest.clients_services.domain.gateways.ClientRepository;
import com.devsutest.clients_services.domain.models.Client;
import com.devsutest.clients_services.infrastructure.driveradapter.jparepositorys.entitys.ClientEntity;
import com.devsutest.clients_services.infrastructure.driveradapter.jparepositorys.repositorys.ClientRepositoryJPA;
import com.devsutest.clients_services.infrastructure.driveradapter.jparepositorys.repositorys.mappers.ClientEntityMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class ClientJpaAdapter implements ClientRepository {
    private final ClientRepositoryJPA clientRepositoryJPA;
    private final ClientEntityMapper clientEntityMapper;

    @Override
    @Transactional
    public Client save(Client client) {
        ClientEntity clientEntity = clientEntityMapper.toEntity(client);
        return clientEntityMapper.toDomain(clientRepositoryJPA.save(clientEntity));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Client> findByIdentification(String clientId) {
        return clientRepositoryJPA.findByIdentification(clientId)
                .map(clientEntityMapper::toDomain);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Client> findAll() {
        return clientRepositoryJPA.findAll()
                .stream()
                .map(clientEntityMapper::toDomain)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public Client update(Client client) {
        ClientEntity clientEntity = clientEntityMapper.toEntity(client);
        return clientEntityMapper.toDomain(clientRepositoryJPA.save(clientEntity));
    }

    @Override
    @Transactional(readOnly = true)
    public void delete(String clientId) {
        clientRepositoryJPA.findById(clientId)
                .ifPresent(clientRepositoryJPA::delete);
    }
}
