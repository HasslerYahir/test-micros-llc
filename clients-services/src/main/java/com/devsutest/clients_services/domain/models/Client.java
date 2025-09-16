package com.devsutest.clients_services.domain.models;

import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Client extends Person{
    private String clientId;
    private String password;
    private boolean status;
}
