package com.proyecto_final.ms_credit.application.port.in.dto;

import lombok.Builder;

import java.math.BigDecimal;

/**
 * Datos modificables de un crédito existente.
 */
@Builder
public record UpdateCreditDto(
        String creditId,
        BigDecimal approvedAmount,
        BigDecimal creditLimit
) {
}
