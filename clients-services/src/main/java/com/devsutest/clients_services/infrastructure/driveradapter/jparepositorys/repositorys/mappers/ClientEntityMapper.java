package com.devsutest.clients_services.infrastructure.driveradapter.jparepositorys.repositorys.mappers;

import com.devsutest.clients_services.domain.models.Client;
import com.devsutest.clients_services.infrastructure.driveradapter.jparepositorys.entitys.ClientEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ClientEntityMapper {
    ClientEntity toEntity(Client client);
    Client toDomain(ClientEntity clientEntity);
}
