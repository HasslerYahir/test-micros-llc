package com.devsutest.accounts_services.infraestructure.driveradapter.restconsumer.mappers;

import com.devsutest.accounts_services.domain.model.Client;
import com.devsutest.accounts_services.infraestructure.driveradapter.restconsumer.dto.ClientDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ClientMapper {
    Client toDomain(ClientDTO clientDTO);
}
