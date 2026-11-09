package com.proyecto_final.ms_account.domain.exception;

import lombok.Getter;

/**
 * Se lanza cuando el cliente al que se asocia la cuenta no existe en ms-customer.
 */
@Getter
public class CustomerNotFoundException extends RuntimeException {

    private final String customerId;

    public CustomerNotFoundException(String customerId) {
        super("Customer not found with id: " + customerId);
        this.customerId = customerId;
    }
}
