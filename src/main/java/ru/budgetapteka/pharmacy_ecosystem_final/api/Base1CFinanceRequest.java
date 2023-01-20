package ru.budgetapteka.pharmacy_ecosystem_final.api;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;



import java.time.LocalDate;

@Component
public class Base1CFinanceRequest implements Request {

    private final Connection connection;

    @Value("${base.1c.getFinanceData}")
    private String GET_FINANCE_DATA_GET;

    @Autowired
    public Base1CFinanceRequest(@Qualifier("base1CConnection") Connection base1CConnection) {
        this.connection = base1CConnection;
    }

    @Override
    public JsonNode getData(LocalDate dateFrom, LocalDate dateTo) {
        return connection.getWebClient()
                .get()
                .uri(GET_FINANCE_DATA_GET, dateFrom, dateTo)
                .retrieve()
                .bodyToMono(JsonNode.class)
                .block();
    }

    @Override
    public RequestType getType() {
        return RequestType.STATEMENT_FROM_1C_BASE;
    }


}
