package ru.budgetapteka.pharmacy_ecosystem_final.api;

import org.springframework.http.HttpHeaders;

public class SberBank extends Bank{

    public SberBank() {

    }

    @Override
    HttpHeaders createHeaders(String token) {
        return null;
    }
}
