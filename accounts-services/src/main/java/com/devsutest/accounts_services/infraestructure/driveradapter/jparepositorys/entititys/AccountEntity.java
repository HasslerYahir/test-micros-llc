package com.devsutest.accounts_services.infraestructure.driveradapter.jparepositorys.entititys;

import com.devsutest.accounts_services.domain.model.TypeAccount;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "accounts")
public class AccountEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    private String accountNumber;
    private String clientId;
    private String type;
    private  double amount;
    private boolean status;
    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<AccountMovementsEntity> movements = new ArrayList<>();
}
