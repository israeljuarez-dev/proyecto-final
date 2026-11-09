package com.proyecto_final.ms_account.domain.exception;

import com.proyecto_final.ms_account.domain.model.AccountTypeEnum;
import lombok.Getter;

/**
 * Se lanza cuando el cliente ya tiene el máximo de cuentas activas de ese tipo.
 */
@Getter
public class AccountLimitExceededException extends RuntimeException {

    private final String customerId;
    private final AccountTypeEnum accountType;

    public AccountLimitExceededException(String customerId, AccountTypeEnum accountType) {
        super("Customer " + customerId + " already has the maximum number of " + accountType + " accounts");
        this.customerId = customerId;
        this.accountType = accountType;
    }
}