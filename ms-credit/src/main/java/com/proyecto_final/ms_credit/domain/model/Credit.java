package com.proyecto_final.ms_credit.domain.model;

import java.math.BigDecimal;
import java.time.Instant;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

/**
 * Producto de crédito (activo): préstamo o tarjeta de crédito. Modelo de dominio inmutable:
 * cualquier cambio produce una nueva instancia mediante {@link #toBuilder()}.
 * La deuda y los consumos no se guardan aquí, los gestiona ms-transaction.
 */
@Getter
@Builder(toBuilder = true)
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Credit {

    @EqualsAndHashCode.Include
    private final String id;

    private final String customerId;

    // Copia del tipo del cliente al crear el crédito; el tipo de cliente no cambia
    private final CustomerTypeEnum customerType;

    private final CreditTypeEnum type;

    // Monto propio del préstamo
    private final BigDecimal approvedAmount;

    // Monto propio de la tarjeta de crédito
    private final BigDecimal creditLimit;

    // Soft delete: un crédito eliminado se conserva con isActive = false
    private final boolean isActive;

    private final Instant createdAt;

    private final Instant updatedAt;

    /**
     * Devuelve una copia del crédito con los montos actualizados.
     * El cliente y el tipo de crédito no se modifican porque forman su identidad.
     *
     * @param approvedAmount nuevo monto aprobado
     * @param creditLimit    nuevo límite de crédito
     * @param now            instante de la actualización
     * @return copia actualizada del crédito
     */
    public Credit update(BigDecimal approvedAmount, BigDecimal creditLimit, Instant now) {
        return toBuilder()
                .approvedAmount(approvedAmount)
                .creditLimit(creditLimit)
                .updatedAt(now)
                .build();
    }

    /**
     * Devuelve una copia del crédito marcada como inactiva (eliminación lógica).
     *
     * @param now instante de la eliminación
     * @return copia inactiva del crédito
     */
    public Credit deactivate(Instant now) {
        return toBuilder()
                .isActive(false)
                .updatedAt(now)
                .build();
    }
}
