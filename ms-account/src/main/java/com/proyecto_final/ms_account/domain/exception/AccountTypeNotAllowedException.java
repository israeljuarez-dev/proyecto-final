package com.proyecto_final.ms_account.domain.exception;

import com.proyecto_final.ms_account.domain.model.AccountTypeEnum;
import com.proyecto_final.ms_account.domain.model.CustomerTypeEnum;
import lombok.Getter;

/**
 * Se lanza cuando el tipo de cuenta no se ofrece al tipo de cliente.
 */
@Getter
public class AccountTypeNotAllowedException extends RuntimeException {

    private final CustomerTypeEnum customerType;
    private final AccountTypeEnum accountType;

    public AccountTypeNotAllowedException(CustomerTypeEnum customerType, AccountTypeEnum accountType) {
        super("Account type " + accountType + " is not allowed for customer type " + customerType);
        this.customerType = customerType;
        this.accountType = accountType;
    }
}