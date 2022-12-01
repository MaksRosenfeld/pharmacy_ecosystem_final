package ru.budgetapteka.pharmacy_ecosystem_final.api;


import ru.budgetapteka.pharmacy_ecosystem_final.exceptions.FailInConfiguringApiException;

import java.time.LocalDate;
import java.util.Optional;

public abstract class Api {
    public enum Source {BANK_OPEN, BANK_SBER, BASE_FINANCE, BASE_SALARY}
    protected LocalDate dateFrom;
    LocalDate dateTo;
    Source source;

    abstract Connection createConnection(Source source);

    public Api configure(Source source) {
        this.source = source;
        return this;
    }

    public Api setDate(LocalDate dateFrom, LocalDate dateTo) {
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
        return this;
    }

    public JsonData getJson() {
        Source specifiedSource = Optional.ofNullable(source)
                .orElseThrow(() -> new FailInConfiguringApiException("First specify the source"));
        Connection connection = createConnection(specifiedSource);


        return null;
    }
}
