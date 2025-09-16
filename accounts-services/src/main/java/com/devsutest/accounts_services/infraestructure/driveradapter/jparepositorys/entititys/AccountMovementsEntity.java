package com.devsutest.accounts_services.infraestructure.driveradapter.jparepositorys.entititys;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "accounts_movements")
@With
public class AccountMovementsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    private Instant date;
    private String typeMovement;
    private double amount;
    private double balance;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id", nullable = false)
    private AccountEntity account;
}
