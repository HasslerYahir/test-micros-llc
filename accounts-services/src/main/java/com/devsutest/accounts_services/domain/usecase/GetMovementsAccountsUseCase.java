package com.devsutest.accounts_services.domain.usecase;

import com.devsutest.accounts_services.application.exceptions.DateNotValidException;
import com.devsutest.accounts_services.application.exceptions.UserNotFoundException;
import com.devsutest.accounts_services.domain.gateways.AccountMovementRepository;
import com.devsutest.accounts_services.domain.gateways.ClientGateway;
import com.devsutest.accounts_services.domain.model.AccountMovements;
import com.devsutest.accounts_services.domain.model.Client;
import lombok.RequiredArgsConstructor;

import java.time.Instant;
import java.util.List;

@RequiredArgsConstructor
public class GetMovementsAccountsUseCase {
    private final ClientGateway clientGateway;
    private final AccountMovementRepository accountMovementRepository;

    public List<AccountMovements> getMovementsAccounts(Instant start, Instant end, String identification){
        Client client = clientGateway.findById(identification)
                .orElseThrow(() -> new UserNotFoundException("Client not found"));

        if (start.isAfter(end)) {
            throw new DateNotValidException("Start date is after end date");
        }
        return accountMovementRepository.findByDateAndIdentification(start, end, client.getIdentification());
    }
}
