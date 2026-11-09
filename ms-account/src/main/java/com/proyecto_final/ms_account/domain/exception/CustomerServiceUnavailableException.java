package com.proyecto_final.ms_account.domain.exception;

import lombok.Getter;

/**
 * Se lanza cuando ms-customer no responde o contesta con un error de servidor.
 */
@Getter
public class CustomerServiceUnavailableException extends RuntimeException {

    private final String reason;

    public CustomerServiceUnavailableException(String reason) {
        super("Customer service is unavailable: " + reason);
        this.reason = reason;
    }
}
