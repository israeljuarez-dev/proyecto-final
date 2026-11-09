package com.proyecto_final.ms_account.infrastructure.adapter.in.rest.dto.request;

import com.proyecto_final.ms_account.infrastructure.adapter.in.rest.validation.AccountValidationConstants;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Builder;

import java.math.BigDecimal;
import java.util.List;

/**
 * Solicitud para actualizar las condiciones, titulares y firmantes de una cuenta.
 */
@Builder
public record UpdateAccountRequestDto(

        @Positive(message = "monthlyMovementLimit must be greater than zero")
        Integer monthlyMovementLimit,

        @PositiveOrZero(message = "maintenanceFee must be zero or greater")
        BigDecimal maintenanceFee,

        @Min(value = AccountValidationConstants.MIN_MOVEMENT_DAY, message = "movementDayOfMonth must be between 1 and 28")
        @Max(value = AccountValidationConstants.MAX_MOVEMENT_DAY, message = "movementDayOfMonth must be between 1 and 28")
        Integer movementDayOfMonth,

        @Valid
        List<AccountPersonRequestDto> holders,

        @Valid
        List<AccountPersonRequestDto> signers) {
}