package com.proyecto_final.ms_credit.application.port.in.dto;

import com.proyecto_final.ms_credit.domain.model.CreditTypeEnum;
import lombok.Builder;

import java.math.BigDecimal;

/**
 * Datos necesarios para crear un crédito.
 */
@Builder
public record CreateCreditDto(
        String customerId,
        CreditTypeEnum type,
        BigDecimal approvedAmount,
        BigDecimal creditLimit
) {
}
