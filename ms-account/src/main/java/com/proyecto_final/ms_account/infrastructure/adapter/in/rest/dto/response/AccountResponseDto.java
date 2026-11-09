package com.proyecto_final.ms_account.infrastructure.adapter.in.rest.dto.response;

import com.proyecto_final.ms_account.domain.model.AccountTypeEnum;
import com.proyecto_final.ms_account.domain.model.CustomerTypeEnum;
import lombok.Builder;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

/**
 * Respuesta con los datos de una cuenta.
 */
@Builder
public record AccountResponseDto(
        String id,
        String customerId,
        CustomerTypeEnum customerType,
        AccountTypeEnum type,
        Integer monthlyMovementLimit,
        BigDecimal maintenanceFee,
        Integer movementDayOfMonth,
        List<AccountPersonResponseDto> holders,
        List<AccountPersonResponseDto> signers,
        Instant createdAt,
        Instant updatedAt
) {
}