package com.devsutest.accounts_services.infraestructure.driveradapter.jparepositorys.adapter;

import com.devsutest.accounts_services.domain.gateways.AccountMovementRepository;
import com.devsutest.accounts_services.domain.model.Account;
import com.devsutest.accounts_services.domain.model.AccountMovements;
import com.devsutest.accounts_services.infraestructure.driveradapter.jparepositorys.entititys.AccountEntity;
import com.devsutest.accounts_services.infraestructure.driveradapter.jparepositorys.entititys.AccountMovementsEntity;
import com.devsutest.accounts_services.infraestructure.driveradapter.jparepositorys.mappers.AccountMapperRepository;
import com.devsutest.accounts_services.infraestructure.driveradapter.jparepositorys.mappers.AccountMovementsMapper;
import com.devsutest.accounts_services.infraestructure.driveradapter.jparepositorys.repositorys.AccountMovementsRepositoryJPA;
import com.devsutest.accounts_services.infraestructure.driveradapter.jparepositorys.repositorys.AccountRepositoryJPA;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.time.ZoneId;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class AccountMovementsAdapterRepositoryJPA implements AccountMovementRepository {
    private final AccountMovementsRepositoryJPA repository;
    private final AccountRepositoryJPA accountRepository;
    private final AccountMapperRepository accountMapper;
    private final AccountMovementsMapper accountMovementsMapper;

    @Override
    @Transactional
    public AccountMovements save(Account account, AccountMovements accountMovements) {
        AccountEntity accountEntity = accountMapper.toEntity(account);

        AccountMovementsEntity movementEntity = accountMovementsMapper.toEntity(accountMovements)
                .withAccount(accountEntity);

        accountRepository.updateAccountBalance(accountEntity.getAccountNumber(), accountEntity.getAmount());
        return accountMovementsMapper.toDomain(repository.save(movementEntity));
    }

    @Override
    @Transactional(readOnly = true)
    public List<AccountMovements> findByDateAndIdentification(Instant start, Instant end, String identification) {
        ZoneId zoneId = ZoneId.of("America/Bogota");
        return repository.findAllByAccount_ClientIdAndDateBetween(identification,start.atZone(zoneId).toInstant(), end.atZone(zoneId).toInstant())
                .stream()
                .map(this.accountMovementsMapper::toDomain)
                .toList();
    }
}
