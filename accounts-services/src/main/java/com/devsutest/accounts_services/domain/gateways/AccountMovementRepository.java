package com.devsutest.accounts_services.domain.gateways;

import com.devsutest.accounts_services.domain.model.Account;
import com.devsutest.accounts_services.domain.model.AccountMovements;

import java.time.Instant;
import java.util.List;

public interface AccountMovementRepository {
    AccountMovements save(Account account, AccountMovements accountMovements);
    List<AccountMovements> findByDateAndIdentification(Instant start, Instant end, String identification);
}
