package com.devsutest.clients_services.infrastructure.driveradapter.jparepositorys.repositorys;

import com.devsutest.clients_services.infrastructure.driveradapter.jparepositorys.entitys.PersonEntity;
import org.springframework.data.jpa.repository.JpaRepository;


public interface PersonRepositoryJPA extends JpaRepository<PersonEntity, String> {
}
