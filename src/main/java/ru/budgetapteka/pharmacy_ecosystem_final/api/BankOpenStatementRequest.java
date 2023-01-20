package ru.budgetapteka.pharmacy_ecosystem_final.api;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import reactor.util.retry.Retry;

import java.time.Duration;
import java.time.LocalDate;

@Slf4j
@Component
public class BankOpenStatementRequest implements Request {
    private final Connection connection;

    @Value("${bank.open.api.postOrderStatement}")
    private String POST_ORDER_STATEMENT;

    @Value("${bank.open.api.getStatementGet}")
    private String GET_STATEMENT_GET;
    @Value("${bank.open.api.statementIdPath}")
    private String STATEMENT_ID_PATH;

    @Autowired
    public BankOpenStatementRequest(
            @Qualifier("bankOpenConnection") Connection openConnection) {
        this.connection = openConnection;
    }

    @Override
    public JsonNode getData(LocalDate dateFrom, LocalDate dateTo) {
        Long statementId = orderStatement(dateFrom, dateTo);
        return getStatement(statementId);
    }

    @Override
    public RequestType getType() {
        return RequestType.STATEMENT_FROM_OPEN_BANK;
    }

    private Long orderStatement(LocalDate dateFrom, LocalDate dateTo) {
        log.info("Заказываем выписку с {} по {}", dateFrom, dateTo);
        return connection.getWebClient()
                .post()
                .uri(POST_ORDER_STATEMENT, dateFrom, dateTo)
                .retrieve()
                .bodyToMono(JsonNode.class)
                .doOnError(e -> log.info("Неудачная попытка заказа выписки по банку: {}", e.getMessage()))
                .retryWhen(Retry.backoff(2, Duration.ofSeconds(10)))
                .map(jn -> jn.at(STATEMENT_ID_PATH).asLong())
                .block();
    }

    private JsonNode getStatement(Long statementId) {
        log.info("Получаем выписку");
        return connection.getWebClient()
                .get()
                .uri(GET_STATEMENT_GET, statementId)
                .retrieve()
                .bodyToMono(JsonNode.class)
                .block();
    }


}
