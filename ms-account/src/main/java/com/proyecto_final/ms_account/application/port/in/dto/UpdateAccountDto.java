package com.proyecto_final.ms_account.application.port.in.dto;

import com.proyecto_final.ms_account.domain.model.AccountPerson;
import lombok.Builder;

import java.math.BigDecimal;
import java.util.List;

/**
 * Datos modificables de una cuenta existente. Las listas no son nulas (pueden ir vacías).
 */
@Builder
public record UpdateAccountDto(
        String accountId,
        Integer monthlyMovementLimit,
        BigDecimal maintenanceFee,
        Integer movementDayOfMonth,
        List<AccountPerson> holders,
        List<AccountPerson> signers
) {
}
