package com.proyecto_final.ms_account.application.port.in.dto;

import com.proyecto_final.ms_account.domain.model.AccountPerson;
import com.proyecto_final.ms_account.domain.model.AccountTypeEnum;
import lombok.Builder;

import java.math.BigDecimal;
import java.util.List;

/**
 * Datos necesarios para abrir una cuenta. Las listas de titulares y firmantes no son nulas
 * (pueden ir vacías).
 */
@Builder
public record CreateAccountDto(
        String customerId,
        AccountTypeEnum type,
        Integer monthlyMovementLimit,
        BigDecimal maintenanceFee,
        Integer movementDayOfMonth,
        List<AccountPerson> holders,
        List<AccountPerson> signers
) {
}
