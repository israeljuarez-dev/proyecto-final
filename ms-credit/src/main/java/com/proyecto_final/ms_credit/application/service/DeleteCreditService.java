package com.proyecto_final.ms_credit.application.service;

import com.proyecto_final.ms_credit.application.port.in.use_cases.DeleteCreditUseCase;
import com.proyecto_final.ms_credit.application.port.in.use_cases.FindCreditByIdUseCase;
import com.proyecto_final.ms_credit.application.port.out.CreditRepositoryPort;
import io.reactivex.rxjava3.core.Completable;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Clock;

/**
 * Implementa el caso de uso DeleteCreditUseCase del crédito: orquesta las reglas del negocio.
 */
@Service
@RequiredArgsConstructor
public class DeleteCreditService implements DeleteCreditUseCase {

    private final FindCreditByIdUseCase findCreditByIdUseCase;

    private final CreditRepositoryPort creditRepositoryPort;

    private final Clock clock;

    /**
     * {@inheritDoc}
     */
    @Override
    public Completable delete(String creditId) {
        return findCreditByIdUseCase.findById(creditId)
                .map(credit -> credit.deactivate(clock.instant()))
                .flatMap(creditRepositoryPort::save)
                .ignoreElement();
    }
}
