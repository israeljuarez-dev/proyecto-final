package com.proyecto_final.ms_credit.application.service;

import com.proyecto_final.ms_credit.application.port.in.use_cases.FindCreditByIdUseCase;
import com.proyecto_final.ms_credit.application.port.out.CreditRepositoryPort;
import com.proyecto_final.ms_credit.domain.exception.CreditNotFoundException;
import com.proyecto_final.ms_credit.domain.model.Credit;
import io.reactivex.rxjava3.core.Single;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * Implementa el caso de uso FindCreditByIdUseCase del crédito: orquesta las reglas del negocio.
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class FindCreditByIdService implements FindCreditByIdUseCase {

    private final CreditRepositoryPort creditRepositoryPort;

    /**
     * {@inheritDoc}
     */
    @Override
    public Single<Credit> findById(String creditId) {
        return creditRepositoryPort.findActiveById(creditId)
                .switchIfEmpty(Single.defer(() -> {
                    log.warn("Credit not found, id {}", creditId);
                    return Single.error(new CreditNotFoundException(creditId));
                }));
    }
}
