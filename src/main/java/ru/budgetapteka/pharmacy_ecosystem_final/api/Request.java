package ru.budgetapteka.pharmacy_ecosystem_final.api;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;

public interface Request {
    JsonNode getData(LocalDate dateFrom, LocalDate dateTo);
    RequestType getType();

    @Autowired
    default void registerRequest(RequestFactory requestFactory) {
        requestFactory.register(this);
    }

}
