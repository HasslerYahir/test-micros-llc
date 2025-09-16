package com.devsutest.clients_services.infrastructure.driveradapter.jparepositorys.entitys;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="persons")
@Inheritance(strategy = InheritanceType.JOINED)
@Getter
@Setter
public class PersonEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    private String name;
    private String gender;
    private int age;
    private String identification;
    private String address;
    private String phone;
}
