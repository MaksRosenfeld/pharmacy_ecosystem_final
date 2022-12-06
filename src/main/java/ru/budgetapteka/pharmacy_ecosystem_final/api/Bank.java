package ru.budgetapteka.pharmacy_ecosystem_final.api;

import org.springframework.http.HttpHeaders;

public abstract class Bank extends Connection {

    public Bank() {
        createWebClient();
    }

    abstract HttpHeaders createHeaders(String token);
}
