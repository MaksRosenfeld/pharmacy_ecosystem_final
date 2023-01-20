package ru.budgetapteka.pharmacy_ecosystem_final.api;

import com.fasterxml.jackson.databind.JsonNode;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class Base1CFinanceRequestTest extends AbstractApiTest {

    @Autowired
    RequestFactory requestFactory;
    @Qualifier("base1CConnection")
    @Autowired
    Connection connection;

    @BeforeEach
    void setUp() {
        WebClient mockWebClient = WebClient.builder().baseUrl(mockBaseUrl).build();
        connection.setWebClient(mockWebClient);
    }

    @Test
    void checkDataFrom1CBase() {
        DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE;
        LocalDate dateFrom = LocalDate.parse("2023-01-18", formatter);
        LocalDate dateTo = LocalDate.parse("2023-02-18", formatter);
        JsonNode data = connection.getData(dateFrom, dateTo,
                requestFactory.createRequest(RequestType.STATEMENT_FROM_1C_BASE));
        long turnOver = data.get(1).get("turnOver").asLong();
        assertEquals(135400.00, turnOver);
    }
}