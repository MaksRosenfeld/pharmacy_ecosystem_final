package ru.budgetapteka.pharmacy_ecosystem_final;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties
public class PharmacyEcosystemFinalApplication {

    public static void main(String[] args) {
        SpringApplication.run(PharmacyEcosystemFinalApplication.class, args);
    }

}
