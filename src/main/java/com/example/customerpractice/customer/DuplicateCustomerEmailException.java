package com.example.customerpractice.customer;

public class DuplicateCustomerEmailException extends RuntimeException {
    public DuplicateCustomerEmailException(String email) {
        super("Customer email already exists: " + email);
    }
}
