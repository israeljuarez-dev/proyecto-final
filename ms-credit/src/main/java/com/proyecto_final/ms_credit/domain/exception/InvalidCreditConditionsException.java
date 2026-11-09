package com.proyecto_final.ms_credit.domain.exception;

import com.proyecto_final.ms_credit.domain.model.CreditTypeEnum;
import lombok.Getter;

/**
 * Se lanza cuando los montos enviados no corresponden al tipo de crédito.
 */
@Getter
public class InvalidCreditConditionsException extends RuntimeException {

    private final CreditTypeEnum creditType;

    public InvalidCreditConditionsException(CreditTypeEnum creditType) {
        super("Amounts do not match credit type " + creditType
                + ": send only the amount required by that type");
        this.creditType = creditType;
    }
}