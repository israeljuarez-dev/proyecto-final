package com.proyecto_final.ms_account.domain.model;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

/**
 * Cuenta bancaria (pasivo)
 */
@Getter
@Builder(toBuilder = true)
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Account {

    @EqualsAndHashCode.Include
    private final String id;

    private final String customerId;

    // Copia del tipo del cliente al abrir la cuenta; el tipo de cliente no cambia
    private final CustomerTypeEnum customerType;

    private final AccountTypeEnum type;

    // Condición propia de la cuenta de ahorro
    private final Integer monthlyMovementLimit;

    // Condición propia de la cuenta corriente
    private final BigDecimal maintenanceFee;

    // Condición propia de la cuenta a plazo fijo
    private final Integer movementDayOfMonth;

    private final List<AccountPerson> holders;

    private final List<AccountPerson> signers;

    // Soft delete: una cuenta eliminada se conserva con isActive = false
    private final boolean isActive;

    private final Instant createdAt;

    private final Instant updatedAt;

    /**
     * Devuelve una copia de la cuenta con las condiciones, titulares y firmantes actualizados.
     * El cliente y el tipo de cuenta no se modifican porque forman su identidad.
     *
     * @param monthlyMovementLimit nuevo límite mensual de movimientos
     * @param maintenanceFee       nueva comisión de mantenimiento
     * @param movementDayOfMonth   nuevo día de movimiento
     * @param holders              nuevos titulares
     * @param signers              nuevos firmantes autorizados
     * @param now                  instante de la actualización
     * @return copia actualizada de la cuenta
     */
    public Account update(
            Integer monthlyMovementLimit,
            BigDecimal maintenanceFee,
            Integer movementDayOfMonth,
            List<AccountPerson> holders,
            List<AccountPerson> signers,
            Instant now) {
        return toBuilder()
                .monthlyMovementLimit(monthlyMovementLimit)
                .maintenanceFee(maintenanceFee)
                .movementDayOfMonth(movementDayOfMonth)
                .holders(holders)
                .signers(signers)
                .updatedAt(now)
                .build();
    }

    /**
     * Devuelve una copia de la cuenta marcada como inactiva (eliminación lógica).
     *
     * @param now instante de la eliminación
     * @return copia inactiva de la cuenta
     */
    public Account deactivate(Instant now) {
        return toBuilder()
                .isActive(false)
                .updatedAt(now)
                .build();
    }
}
