package ru.budgetapteka.pharmacy_ecosystem_final.exceptions;

public class NoStatementIdErrorException extends RuntimeException{
    public NoStatementIdErrorException(String message) {
        super(message);
    }
}
