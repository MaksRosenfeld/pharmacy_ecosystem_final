package ru.budgetapteka.pharmacy_ecosystem_final.api;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;


@Component
@ConfigurationProperties("open.bank.api")
public class OpenBank extends Bank {

    @Value("open.bank.api.baseUrl")
    private String baseUrl;
    @Value("open.bank.api.token")
    private String token;

    public OpenBank() {
        setBaseUrl(baseUrl);
        setHttpHeaders(createHeaders(token));
    }

    @Override
    HttpHeaders createHeaders(String token) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        httpHeaders.add(HttpHeaders.AUTHORIZATION, token);
        return httpHeaders;
    }
}
