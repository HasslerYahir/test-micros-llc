package com.devsutest.accounts_services.domain.usecase;

import com.devsutest.accounts_services.application.exceptions.UserNotFoundException;
import com.devsutest.accounts_services.domain.gateways.AccountRepository;
import com.devsutest.accounts_services.domain.gateways.ClientGateway;
import com.devsutest.accounts_services.domain.model.Account;
import com.devsutest.accounts_services.domain.model.Client;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class GetAccountsByIdentificationUseCase {
    private final AccountRepository accountRepository;
    private final ClientGateway clientGateway;
    public List<Account> execute(String identification){
        Client client = clientGateway.findById(identification)
                .orElseThrow(() -> new UserNotFoundException("Client not found"));
        return accountRepository.findByAllIdentification(client.getIdentification());
    }
}
