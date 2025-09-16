package com.devsutest.clients_services.infrastructure.driveradapter.jparepositorys.repositorys;

import com.devsutest.clients_services.infrastructure.driveradapter.jparepositorys.entitys.ClientEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface ClientRepositoryJPA extends JpaRepository<ClientEntity, String> {
    Optional<ClientEntity> findByIdentification(String identification);
}
