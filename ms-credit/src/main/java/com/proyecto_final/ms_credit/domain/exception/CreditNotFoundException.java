package com.proyecto_final.ms_credit.domain.exception;

import lombok.Getter;

/**
 * Se lanza cuando no existe un crédito activo con el identificador solicitado.
 */
@Getter
public class CreditNotFoundException extends RuntimeException {

    private final String creditId;

    public CreditNotFoundException(String creditId) {
        super("Credit not found with id: " + creditId);
        this.creditId = creditId;
    }
}
