package com.devsutest.accounts_services.domain.model;

import com.devsutest.accounts_services.application.exceptions.TypeAccountNotFoundException;
import lombok.Getter;

@Getter
public enum TypeAccount {
    ACCOUNT_SAVINGS(1,"SAVINGS"),
    ACCOUNT_CURRENT(2,"CURRENT");
    private final int id;
    private final String name;
    TypeAccount(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public static TypeAccount fromName(String name) {
        for (TypeAccount type : values()) {
            if (type.getName().equals(name)) {
                return type;
            }
        }
        throw new TypeAccountNotFoundException("No TypeAccount with name: " + name);
    }
}
