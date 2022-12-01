package ru.budgetapteka.pharmacy_ecosystem_final.exceptions;

public class NoSuchBankException extends RuntimeException {
    public NoSuchBankException(String message) {
        super(message);
    }
}
