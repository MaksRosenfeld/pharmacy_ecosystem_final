package ru.budgetapteka.pharmacy_ecosystem_final.api;

import jakarta.annotation.PostConstruct;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Slf4j
@Getter(AccessLevel.PACKAGE)
@Setter(AccessLevel.PACKAGE)
@Component("base1CConnection")
public class Base1CConnection extends Connection {
    @Value("${base.1c.baseUrl}")
    private String defaultBaseUrl;

    @Value("${base.1c.username}")
    private String user;
    @Value("${base.1c.password}")
    private String password;


    @PostConstruct
    @Override
    public void establishConnection() {
        WebClient webClient = WebClient.builder()
                .baseUrl(defaultBaseUrl)
                .defaultHeaders((header) -> {
                    header.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
                    header.setBasicAuth(user, password);
                })
                .build();
        super.setWebClient(webClient);
    }
}
