package com.devsutest.accounts_services.infraestructure.driveradapter.jparepositorys.mappers;

import com.devsutest.accounts_services.domain.model.AccountMovements;
import com.devsutest.accounts_services.infraestructure.driveradapter.jparepositorys.entititys.AccountEntity;
import com.devsutest.accounts_services.infraestructure.driveradapter.jparepositorys.entititys.AccountMovementsEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AccountMovementsMapper {
    AccountMovementsEntity toEntity(AccountMovements accountMovements);
    AccountMovements toDomain(AccountMovementsEntity accountMovementsEntity);
}
