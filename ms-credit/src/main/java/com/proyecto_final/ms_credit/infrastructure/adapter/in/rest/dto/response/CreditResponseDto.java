package com.proyecto_final.ms_credit.infrastructure.adapter.in.rest.dto.response;
import com.proyecto_final.ms_credit.domain.model.CreditTypeEnum;
import com.proyecto_final.ms_credit.domain.model.CustomerTypeEnum;
import lombok.Builder;

import java.math.BigDecimal;
import java.time.Instant;

/**
 * Respuesta con los datos de un crédito.
 */
@Builder
public record CreditResponseDto(
        String id,
        String customerId,
        CustomerTypeEnum customerType,
        CreditTypeEnum type,
        BigDecimal approvedAmount,
        BigDecimal creditLimit,
        Instant createdAt,
        Instant updatedAt
) {
}
