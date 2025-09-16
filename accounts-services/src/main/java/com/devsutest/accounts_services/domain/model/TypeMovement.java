package com.devsutest.accounts_services.domain.model;

import com.devsutest.accounts_services.application.exceptions.TypeMovementNotFoundException;
import lombok.Getter;

@Getter
public enum TypeMovement {
    DEPOSIT(1,"DEPOSIT"),
    WITHDRAWAL(2,"WITHDRAWAL");
    private final int id;
    private final String name;
    TypeMovement(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public static TypeMovement fromName(String name) {
        for (TypeMovement type : values()) {
            if (type.getName().equals(name)) {
                return type;
            }
        }
        throw new TypeMovementNotFoundException("No TypeAccount with name: " + name);
    }
}
