package com.proyecto_final.ms_customer.domain.exception;

import lombok.Getter;

/**
 * Se lanza cuando no existe un cliente activo con el identificador solicitado.
 */
@Getter
public class CustomerNotFoundException extends RuntimeException {

    private final String customerId;

    public CustomerNotFoundException(String customerId) {
        super("Customer not found with id: " + customerId);
        this.customerId = customerId;
    }
}
