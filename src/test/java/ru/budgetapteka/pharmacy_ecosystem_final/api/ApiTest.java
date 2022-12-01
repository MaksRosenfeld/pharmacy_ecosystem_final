package ru.budgetapteka.pharmacy_ecosystem_final.api;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.budgetapteka.pharmacy_ecosystem_final.exceptions.FailInConfiguringApiException;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ApiTest {

    Api api;

    @BeforeEach
    void setUpApi() {
        this.api = new BankApi();
    }

    @DisplayName("Check on JsonData")
    @Test
    void checkTheSource() {
        assertThrows(FailInConfiguringApiException.class, () -> api.getJson());
        JsonData json = api.configure(Api.Source.BANK_OPEN)
                .getJson();
        assertInstanceOf(JsonData.class, json, "Has to be JsonData");

    }

}