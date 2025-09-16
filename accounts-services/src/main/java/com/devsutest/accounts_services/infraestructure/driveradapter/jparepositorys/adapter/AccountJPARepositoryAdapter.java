package com.devsutest.accounts_services.infraestructure.driveradapter.jparepositorys.adapter;

import com.devsutest.accounts_services.domain.gateways.AccountRepository;
import com.devsutest.accounts_services.domain.model.Account;
import com.devsutest.accounts_services.infraestructure.driveradapter.jparepositorys.entititys.AccountEntity;
import com.devsutest.accounts_services.infraestructure.driveradapter.jparepositorys.mappers.AccountMapperRepository;
import com.devsutest.accounts_services.infraestructure.driveradapter.jparepositorys.repositorys.AccountRepositoryJPA;
import com.devsutest.accounts_services.infraestructure.entrypoints.rest.mappers.AccountMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class AccountJPARepositoryAdapter implements AccountRepository {
    private final AccountRepositoryJPA accountRepositoryJPA;
    private final AccountMapperRepository accountMapper;

    @Override
    @Transactional
    public Account save(Account account) {
        AccountEntity saveAccount = accountRepositoryJPA.save(accountMapper.toEntity(account));
        return account
                .withId(saveAccount.getId());
    }

    @Override
    @Transactional
    public Account update(Account account) {
        AccountEntity saveAccount = accountRepositoryJPA.save(accountMapper.toEntity(account));
        return this.accountMapper.toDomain(saveAccount);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Account> findByIdentification(String identification) {
        return accountRepositoryJPA.findByClientId(identification)
                .map(this.accountMapper::toDomain);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Account> findByAccount(String accountNumber) {
        return accountRepositoryJPA
                .findByAccountNumber(accountNumber)
                .map(this.accountMapper::toDomain);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Account> findByAllIdentification(String identification) {
        return accountRepositoryJPA.findAllByClientId(identification)
                .stream()
                .map(this.accountMapper::toDomain)
                .toList();
    }
}
