package com.proyecto_final.ms_account.domain.model;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Set;
import lombok.RequiredArgsConstructor;

/**
 * Tipos de cuenta bancaria. Cada tipo define a qué clientes se ofrece, cuántas cuentas
 * activas puede tener un cliente y qué condición propia exige.
 */
@RequiredArgsConstructor
public enum AccountTypeEnum {

    SAVINGS(Set.of(CustomerTypeEnum.PERSONAL), Map.of(CustomerTypeEnum.PERSONAL, 1)),
    CHECKING(Set.of(CustomerTypeEnum.PERSONAL, CustomerTypeEnum.BUSINESS), Map.of(CustomerTypeEnum.PERSONAL, 1)),
    FIXED_TERM(Set.of(CustomerTypeEnum.PERSONAL), Map.of());

    private final Set<CustomerTypeEnum> allowedCustomerTypes;

    // Máximo de cuentas activas de este tipo por tipo de cliente. Si el tipo de cliente no aparece, no hay límite
    private final Map<CustomerTypeEnum, Integer> maxAccountsByCustomerType;

    /**
     * Indica si este tipo de cuenta se ofrece al tipo de cliente indicado.
     *
     * @param customerType tipo de cliente
     * @return true si el cliente puede tener este tipo de cuenta
     */
    public boolean isAllowedFor(CustomerTypeEnum customerType) {
        return allowedCustomerTypes.contains(customerType);
    }

    /**
     * Indica si el cliente ya alcanzó el máximo de cuentas activas de este tipo.
     *
     * @param customerType     tipo de cliente
     * @param existingAccounts cuentas activas de este tipo que el cliente ya tiene
     * @return true si no puede abrir otra cuenta de este tipo
     */
    public boolean isLimitReached(CustomerTypeEnum customerType, long existingAccounts) {
        Integer maxAccounts = maxAccountsByCustomerType.get(customerType);
        return maxAccounts != null && existingAccounts >= maxAccounts;
    }

    /**
     * Indica si las condiciones recibidas corresponden a este tipo de cuenta:
     * se exige únicamente la propia y las otras dos deben venir vacías.
     *
     * @param monthlyMovementLimit límite mensual de movimientos (ahorro)
     * @param maintenanceFee       comisión de mantenimiento (corriente)
     * @param movementDayOfMonth   día del mes permitido para movimientos (plazo fijo)
     * @return true si las condiciones son coherentes con el tipo
     */
    public boolean acceptsConditions(
            Integer monthlyMovementLimit, BigDecimal maintenanceFee, Integer movementDayOfMonth) {
        return switch (this) {
            case SAVINGS -> monthlyMovementLimit != null && maintenanceFee == null && movementDayOfMonth == null;
            case CHECKING -> maintenanceFee != null && monthlyMovementLimit == null && movementDayOfMonth == null;
            case FIXED_TERM -> movementDayOfMonth != null && monthlyMovementLimit == null && maintenanceFee == null;
        };
    }
}
