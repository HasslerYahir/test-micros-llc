package com.devsutest.accounts_services.infraestructure.entrypoints.rest.mappers;

import com.devsutest.accounts_services.domain.model.Account;
import com.devsutest.accounts_services.infraestructure.entrypoints.rest.data.AccountResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AccountMapper {
    @Mapping(target = "type", expression = "java(account.getType().getName())")
    AccountResponseDTO toDTO(Account account);
}
