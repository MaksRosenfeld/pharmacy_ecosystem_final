package ru.budgetapteka.pharmacy_ecosystem_final.api;


import ru.budgetapteka.pharmacy_ecosystem_final.exceptions.NoSuchBankException;

public class BankApi extends Api{



    @Override
    Connection createConnection(Source source) {
        if (source.equals(Source.BANK_OPEN)) {
            return new OpenBank();
        } else if (source.equals(Source.BANK_SBER)) {
            return new SberBank();
        } else throw new NoSuchBankException("No such bank in DataBase");

    }
}
