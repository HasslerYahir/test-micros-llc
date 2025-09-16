package com.devsutest.clients_services.domain.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.With;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class Person {
    private String id;
    private String name;
    private String gender;
    private int age;
    private String identification;
    private String address;
    private String phone;
}
