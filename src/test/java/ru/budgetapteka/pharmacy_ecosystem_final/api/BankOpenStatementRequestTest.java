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

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class BankOpenStatementRequestTest extends AbstractApiTest {

    @Autowired
    RequestFactory requestFactory;


    @Qualifier("bankOpenConnection")
    @Autowired
    Connection connection;


    @BeforeEach
    void setUp() {
        WebClient mockWebClient = WebClient.builder().baseUrl(mockBaseUrl).build();
        connection.setWebClient(mockWebClient);
    }

    @Test
    void checkOrderStatementRequest() {
        DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE;
        LocalDate dateFrom = LocalDate.parse("2023-01-18", formatter);
        LocalDate dateTo = LocalDate.parse("2023-02-18", formatter);
        JsonNode data = connection.getData(dateFrom, dateTo, requestFactory.createRequest(RequestType.STATEMENT_FROM_OPEN_BANK));
        assertEquals(9863L, data.at("/header/refId").asLong());
    }


}