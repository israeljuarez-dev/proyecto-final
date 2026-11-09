package com.proyecto_final.ms_credit.application.validator;

import com.proyecto_final.ms_credit.domain.exception.InvalidCreditConditionsException;
import com.proyecto_final.ms_credit.domain.model.CreditTypeEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

/**
 * Aplica las reglas de negocio de los créditos que comparten la creación y la actualización.
 * La regla en sí vive en el enum del dominio; aquí se registra el rechazo y se lanza la excepción.
 */
@Slf4j
@Component
public class CreditRulesValidator {

    /**
     * Verifica que los montos recibidos correspondan al tipo de crédito.
     */
    public void validateConditions(CreditTypeEnum creditType, BigDecimal approvedAmount, BigDecimal creditLimit) {
        if (!creditType.acceptsConditions(approvedAmount, creditLimit)) {
            log.warn("Credit rejected, amounts do not match credit type {}", creditType);
            throw new InvalidCreditConditionsException(creditType);
        }
    }
}
