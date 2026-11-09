package com.proyecto_final.ms_credit.domain.model;

import java.math.BigDecimal;
import java.util.Map;
import lombok.RequiredArgsConstructor;

/**
 * Tipos de producto de crédito. Cada tipo define cuántos puede tener un cliente
 * y qué monto propio exige.
 */
@RequiredArgsConstructor
public enum CreditTypeEnum {

    LOAN(Map.of(CustomerTypeEnum.PERSONAL, 1)),
    CREDIT_CARD(Map.of());

    // Máximo de créditos activos de este tipo por tipo de cliente. Si el tipo de cliente no aparece, no hay límite
    private final Map<CustomerTypeEnum, Integer> maxCreditsByCustomerType;

    /**
     * Indica si el cliente ya alcanzó el máximo de créditos activos de este tipo.
     *
     * @param customerType    tipo de cliente
     * @param existingCredits créditos activos de este tipo que el cliente ya tiene
     * @return true si no puede tener otro crédito de este tipo
     */
    public boolean isLimitReached(CustomerTypeEnum customerType, long existingCredits) {
        Integer maxCredits = maxCreditsByCustomerType.get(customerType);
        return maxCredits != null && existingCredits >= maxCredits;
    }

    /**
     * Indica si los montos recibidos corresponden a este tipo de crédito:
     * se exige únicamente el propio y el otro debe venir vacío.
     *
     * @param approvedAmount monto aprobado (préstamo)
     * @param creditLimit    límite de crédito (tarjeta)
     * @return true si los montos son coherentes con el tipo
     */
    public boolean acceptsConditions(BigDecimal approvedAmount, BigDecimal creditLimit) {
        return switch (this) {
            case LOAN -> approvedAmount != null && creditLimit == null;
            case CREDIT_CARD -> creditLimit != null && approvedAmount == null;
        };
    }
}
