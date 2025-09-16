package com.devsutest.accounts_services.infraestructure.driveradapter.jparepositorys.repositorys;

import com.devsutest.accounts_services.infraestructure.driveradapter.jparepositorys.entititys.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface AccountRepositoryJPA extends JpaRepository<AccountEntity, String> {
    Optional<AccountEntity> findByAccountNumber(String accountNumber);
    Optional<AccountEntity> findByClientId(String name);
    List<AccountEntity> findAllByClientId(String name);
    @Modifying
    @Query("update AccountEntity a set a.amount = :newBalance where a.accountNumber = :accountNumber")
    void updateAccountBalance(@Param("accountNumber") String accountNumber,
                              @Param("newBalance") double newBalance);

}
