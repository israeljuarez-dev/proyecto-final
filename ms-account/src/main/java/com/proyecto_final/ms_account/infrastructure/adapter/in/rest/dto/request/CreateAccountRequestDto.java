package com.proyecto_final.ms_account.infrastructure.adapter.in.rest.dto.request;

import com.proyecto_final.ms_account.domain.model.AccountTypeEnum;
import com.proyecto_final.ms_account.infrastructure.adapter.in.rest.validation.AccountValidationConstants;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Builder;

import java.math.BigDecimal;
import java.util.List;

/**
 * Solicitud para abrir una cuenta. Solo se debe enviar la condición propia del tipo de cuenta.
 */
@Builder
public record CreateAccountRequestDto(

        @NotBlank(message = "customerId is required")
        String customerId,

        @NotNull(message = "type is required")
        AccountTypeEnum type,

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
