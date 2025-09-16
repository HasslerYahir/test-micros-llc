package com.devsutest.accounts_services.infraestructure.entrypoints.rest.mappers;

import com.devsutest.accounts_services.domain.model.AccountMovements;
import com.devsutest.accounts_services.infraestructure.entrypoints.rest.data.MovementsResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface MovementsMapper {
    @Mapping(target = "bankMovements", expression = "java(accountMovements.getTypeMovement().getName() + \" of value: \" + accountMovements.getAmount())")
    MovementsResponseDTO toDTO(AccountMovements accountMovements);
}
