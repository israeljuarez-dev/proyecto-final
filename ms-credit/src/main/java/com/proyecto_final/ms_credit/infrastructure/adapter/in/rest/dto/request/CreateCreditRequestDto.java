package com.proyecto_final.ms_credit.infrastructure.adapter.in.rest.dto.request;

import com.proyecto_final.ms_credit.domain.model.CreditTypeEnum;
import com.proyecto_final.ms_credit.infrastructure.adapter.in.rest.validation.CreditValidationConstants;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Builder;

import java.math.BigDecimal;

/**
 * Solicitud para crear un crédito. Solo se debe enviar el monto propio del tipo de crédito.
 */
@Builder
public record CreateCreditRequestDto(

        @NotBlank(message = "customerId is required")
        String customerId,

        @NotNull(message = "type is required")
        CreditTypeEnum type,

        @Positive(message = "approvedAmount must be greater than zero")
        @Digits(
                integer = CreditValidationConstants.AMOUNT_INTEGER_DIGITS,
                fraction = CreditValidationConstants.AMOUNT_FRACTION_DIGITS,
                message = "approvedAmount must have at most 12 integer digits and 2 decimals"
        )
        BigDecimal approvedAmount,

        @Positive(message = "creditLimit must be greater than zero")
        @Digits(
                integer = CreditValidationConstants.AMOUNT_INTEGER_DIGITS,
                fraction = CreditValidationConstants.AMOUNT_FRACTION_DIGITS,
                message = "creditLimit must have at most 12 integer digits and 2 decimals"
        )
        BigDecimal creditLimit) {
}