package com.devsutest.accounts_services.domain.usecase;

import com.devsutest.accounts_services.application.exceptions.AccountNotFoundException;
import com.devsutest.accounts_services.application.exceptions.InsufficientBalanceException;
import com.devsutest.accounts_services.domain.gateways.AccountMovementRepository;
import com.devsutest.accounts_services.domain.gateways.AccountRepository;
import com.devsutest.accounts_services.domain.model.Account;
import com.devsutest.accounts_services.domain.model.AccountMovements;
import com.devsutest.accounts_services.domain.model.TypeMovement;
import lombok.RequiredArgsConstructor;

import java.time.Instant;

@RequiredArgsConstructor
public class CreateAccountMovementUseCase {
    private final AccountMovementRepository accountMovementRepository;
    private final AccountRepository accountRepository;

    public void createAccountMovement(String accountNumber,String typeMovement,double amount) {
        Account account = accountRepository.findByAccount(accountNumber)
                .orElseThrow(()-> new AccountNotFoundException("Account not found"));
        TypeMovement type = TypeMovement.fromName(typeMovement);
        double accountBefore = account.getAmount();
        double signedAmount = type == TypeMovement.WITHDRAWAL ? -amount : amount;

        double newBalance = accountBefore + signedAmount;
        if (type == TypeMovement.WITHDRAWAL && accountBefore + signedAmount < 0) {
            throw new InsufficientBalanceException("Saldo insuficiente");
        }
        AccountMovements movement = AccountMovements.builder()
                .date(Instant.now())
                .amount(signedAmount)
                .balance(newBalance)
                .typeMovement(type)
                .build();

        accountMovementRepository.save(account.withAmount(newBalance), movement);
    }

}
