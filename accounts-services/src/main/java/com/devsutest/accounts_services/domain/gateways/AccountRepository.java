package com.devsutest.accounts_services.domain.gateways;

import com.devsutest.accounts_services.domain.model.Account;

import java.util.List;
import java.util.Optional;

public interface AccountRepository {
    Account save(Account account);
    Account update(Account account);
    Optional<Account> findByIdentification(String identification);
    Optional<Account> findByAccount(String accountNumber);
    List<Account> findByAllIdentification(String identification);
}
