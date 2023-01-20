package ru.budgetapteka.pharmacy_ecosystem_final.api;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.LocalDate;

/**
 * Class, that responsible for getting the data
 * from sided api.
 * T - type, that method getData() has to return
 */
@Getter(AccessLevel.PACKAGE)
@Setter(AccessLevel.PACKAGE)
public abstract class Connection {
    private WebClient webClient;

    /**
     * Main method, that sends requests and gets
     * the data in JsonNode
     * @param dateFrom data to be from this date
     * @param dateTo data to be up to this date
     * @param request type of request (made with RequestFactory)
     * @return all results come back in JsonNode
     */
    public JsonNode getData(LocalDate dateFrom, LocalDate dateTo, Request request) {
        return request.getData(dateFrom, dateTo);
    }

    /**
     * All components, that extend Connection
     * have to establish their connection before
     * being in ApplicationContext
     */
    protected abstract void establishConnection();
}
