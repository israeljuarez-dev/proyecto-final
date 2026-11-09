package com.proyecto_final.ms_account.domain.exception;

import com.proyecto_final.ms_account.domain.model.CustomerTypeEnum;
import lombok.Getter;

/**
 * Se lanza cuando los titulares y firmantes no son válidos para el tipo de cliente.
 */
@Getter
public class InvalidAccountParticipantsException extends RuntimeException {

    private final CustomerTypeEnum customerType;

    public InvalidAccountParticipantsException(CustomerTypeEnum customerType) {
        super("Invalid holders and signers for customer type " + customerType);
        this.customerType = customerType;
    }
}
