package com.proyecto_final.ms_credit.application.service;

import com.proyecto_final.ms_credit.application.port.in.use_cases.FindAllCreditsUseCase;
import com.proyecto_final.ms_credit.application.port.out.CreditRepositoryPort;
import com.proyecto_final.ms_credit.domain.model.Credit;
import io.reactivex.rxjava3.core.Flowable;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * Implementa el caso de uso FindAllCreditsUseCase del crédito: orquesta las reglas del negocio.
 */
@Service
@RequiredArgsConstructor
public class FindAllCreditsService implements FindAllCreditsUseCase {

    private final CreditRepositoryPort creditRepositoryPort;

    /**
     * {@inheritDoc}
     */
    @Override
    public Flowable<Credit> findAll() {
        return creditRepositoryPort.findAllActive();
    }
}
