package com.devsutest.accounts_services.domain.usecase;

import com.devsutest.accounts_services.application.exceptions.NotAccountAvailableException;
import com.devsutest.accounts_services.application.exceptions.UserNotFoundException;
import com.devsutest.accounts_services.domain.gateways.AccountRepository;
import com.devsutest.accounts_services.domain.gateways.ClientGateway;
import com.devsutest.accounts_services.domain.model.Account;
import com.devsutest.accounts_services.domain.model.Client;
import com.devsutest.accounts_services.domain.model.TypeAccount;
import lombok.RequiredArgsConstructor;

import java.util.concurrent.ThreadLocalRandom;

@RequiredArgsConstructor
public class CreateAccountUseCase {
    private final AccountRepository accountRepository;
    private final ClientGateway clientGateway;


    public Account createAccount(String typeAccount, String idClient) {

        Client client = clientGateway.findById(idClient)
                .orElseThrow(() -> new UserNotFoundException("Client not found"));

        TypeAccount type = TypeAccount.fromName(typeAccount);

       boolean accountStatus = accountRepository.findByAllIdentification(idClient)
                .stream()
                .anyMatch(account -> account.getType().equals(type) && account.isStatus());

        if (accountStatus) {
            throw new NotAccountAvailableException("Account Exist");
        }
        String accountNumber = generateUniqueAccountNumber();

        return accountRepository.save(Account.builder()
                .accountNumber(accountNumber)
                .amount(0d)
                .type(type)
                .status(Boolean.TRUE)
                .client(client)
                .build());

    }

    private String generateUniqueAccountNumber() {
        for (int i = 0; i < 10; i++) {
            String accountNumber = generateAccountNumber();
            boolean exists = accountRepository.findByAccount(accountNumber).isPresent();
            if (!exists) {
                return accountNumber;
            }
        }

        throw new NotAccountAvailableException("Account not available");
    }

    private String generateAccountNumber() {
        int prefix = ThreadLocalRandom.current().nextBoolean() ? 1 : 9;
        int rest = ThreadLocalRandom.current().nextInt(1_000_000, 10_000_000);
        return prefix + String.format("%07d", rest);
    }


}
