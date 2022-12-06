package ru.budgetapteka.pharmacy_ecosystem_final.api;


import ru.budgetapteka.pharmacy_ecosystem_final.exceptions.NoSuchBase1CException;

public class Base1CApi extends Api {
    @Override
    public Connection createConnection(Source source) {
        if (source.equals(Source.BASE_FINANCE)) {
            return new FinanceBase1C();
        } else if (source.equals(Source.BASE_SALARY)) {
            return new SalaryBase1C();
        } else throw new NoSuchBase1CException("No such base of 1C in DataBase");

    }
}
