package com.proyecto_final.ms_account.domain.exception;

import com.proyecto_final.ms_account.domain.model.AccountTypeEnum;
import lombok.Getter;

/**
 * Se lanza cuando las condiciones enviadas no corresponden al tipo de cuenta.
 */
@Getter
public class InvalidAccountConditionsException extends RuntimeException {

    private final AccountTypeEnum accountType;

    public InvalidAccountConditionsException(AccountTypeEnum accountType) {
        super("Conditions do not match account type " + accountType
                + ": send only the condition required by that type");
        this.accountType = accountType;
    }
}
