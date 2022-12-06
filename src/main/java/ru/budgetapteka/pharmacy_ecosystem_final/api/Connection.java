package ru.budgetapteka.pharmacy_ecosystem_final.api;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;

@Slf4j
public abstract class Connection {

    WebClient webClient;

    // template method
    final JsonData getJson() {

        return null;
    }

    void createWebClient() {
        this.webClient = WebClient.builder().exchangeStrategies(ExchangeStrategies.builder()
                        .codecs(configurer -> configurer
                                .defaultCodecs()
                                .maxInMemorySize(16 * 1024 * 1024))
                        .build())
                .build();
    }

    void setBaseUrl(String baseUrl) {
        webClient
                .options()
                .uri(baseUrl);
    }

    void setHttpHeaders(HttpHeaders headers) {
        webClient
                .options()
                .headers(httpHeaders -> httpHeaders.addAll(headers));
    }

}
