package com.proyecto_final.ms_account.domain.exception;

import lombok.Getter;

/**
 * Se lanza cuando no existe una cuenta activa con el identificador solicitado.
 */
@Getter
public class AccountNotFoundException extends RuntimeException {

    private final String accountId;

    public AccountNotFoundException(String accountId) {
        super("Account not found with id: " + accountId);
        this.accountId = accountId;
    }
}
