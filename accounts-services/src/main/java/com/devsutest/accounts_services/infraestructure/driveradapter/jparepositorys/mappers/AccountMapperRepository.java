package com.devsutest.accounts_services.infraestructure.driveradapter.jparepositorys.mappers;

import com.devsutest.accounts_services.domain.model.Account;
import com.devsutest.accounts_services.domain.model.TypeAccount;
import com.devsutest.accounts_services.infraestructure.driveradapter.jparepositorys.entititys.AccountEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring",imports = {
        TypeAccount.class
})
public interface AccountMapperRepository {
    @Mapping(target = "clientId", source = "client.identification")
    @Mapping(target = "type", expression = "java(account.getType().getName())")
    AccountEntity toEntity(Account account);

    @Mapping(target = "client.identification", source = "clientId")
    @Mapping(target = "type", expression = "java(TypeAccount.fromName(accountEntity.getType()))")
    Account toDomain(AccountEntity accountEntity);
}
