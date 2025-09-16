package com.devsutest.accounts_services.infraestructure.driveradapter.jparepositorys.repositorys;

import com.devsutest.accounts_services.infraestructure.driveradapter.jparepositorys.entititys.AccountMovementsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.Instant;
import java.util.List;

public interface AccountMovementsRepositoryJPA extends JpaRepository<AccountMovementsEntity,String> {
    List<AccountMovementsEntity> findAllByAccount_ClientIdAndDateBetween(
            String clientId,
            Instant startDate,
            Instant endDate
    );
}
