package com.devsutest.clients_services.infrastructure.entrypoints.rest.mappers;

import com.devsutest.clients_services.domain.models.Client;
import com.devsutest.clients_services.infrastructure.entrypoints.rest.data.ClientDTO;
import com.devsutest.clients_services.infrastructure.entrypoints.rest.data.RegisterClientRequest;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ClientMapper {
    Client toDomain(RegisterClientRequest request);
    ClientDTO toDTO(Client client);
}
