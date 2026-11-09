package com.proyecto_final.ms_credit.application.service;

import com.proyecto_final.ms_credit.application.port.in.dto.UpdateCreditDto;
import com.proyecto_final.ms_credit.application.port.in.use_cases.FindCreditByIdUseCase;
import com.proyecto_final.ms_credit.application.port.in.use_cases.UpdateCreditUseCase;
import com.proyecto_final.ms_credit.application.port.out.CreditRepositoryPort;
import com.proyecto_final.ms_credit.application.validator.CreditRulesValidator;
import com.proyecto_final.ms_credit.domain.model.Credit;
import io.reactivex.rxjava3.core.Single;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Clock;

/**
 * Implementa el caso de uso UpdateCreditUseCase del crédito: orquesta las reglas del negocio.
 */
@Service
@RequiredArgsConstructor
public class UpdateCreditService implements UpdateCreditUseCase {

    private final FindCreditByIdUseCase findCreditByIdUseCase;

    private final CreditRepositoryPort creditRepositoryPort;

    private final CreditRulesValidator creditRulesValidator;

    private final Clock clock;

    /**
     * {@inheritDoc}
     */
    @Override
    public Single<Credit> update(UpdateCreditDto updateCreditDto) {
        return findCreditByIdUseCase.findById(updateCreditDto.creditId())
                .map(credit -> applyUpdate(credit, updateCreditDto))
                .flatMap(creditRepositoryPort::save);
    }

    /**
     * Valida los montos nuevos contra el tipo de crédito y devuelve el crédito actualizado.
     */
    private Credit applyUpdate(Credit credit, UpdateCreditDto updateCreditDto) {
        creditRulesValidator.validateConditions(
                credit.getType(),
                updateCreditDto.approvedAmount(),
                updateCreditDto.creditLimit()
        );

        return credit.update(
                updateCreditDto.approvedAmount(),
                updateCreditDto.creditLimit(),
                clock.instant());
    }
}
