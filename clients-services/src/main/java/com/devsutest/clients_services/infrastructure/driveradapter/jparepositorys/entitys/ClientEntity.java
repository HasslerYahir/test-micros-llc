package com.devsutest.clients_services.infrastructure.driveradapter.jparepositorys.entitys;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "clients")
@PrimaryKeyJoinColumn(name = "id")
@Getter
@Setter
public class ClientEntity extends PersonEntity {
    private String clientId;
    private String password;
    private boolean status = true;
}
