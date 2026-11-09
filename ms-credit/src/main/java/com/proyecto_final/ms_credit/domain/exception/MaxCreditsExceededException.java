package com.proyecto_final.ms_credit.domain.exception;

import com.proyecto_final.ms_credit.domain.model.CreditTypeEnum;
import lombok.Getter;

/**
 * Se lanza cuando el cliente ya tiene el máximo de créditos activos de ese tipo.
 */
@Getter
public class MaxCreditsExceededException extends RuntimeException {

    private final String customerId;
    private final CreditTypeEnum creditType;

    public MaxCreditsExceededException(String customerId, CreditTypeEnum creditType) {
        super("Customer " + customerId + " already has the maximum number of " + creditType + " credits");
        this.customerId = customerId;
        this.creditType = creditType;
    }
}
