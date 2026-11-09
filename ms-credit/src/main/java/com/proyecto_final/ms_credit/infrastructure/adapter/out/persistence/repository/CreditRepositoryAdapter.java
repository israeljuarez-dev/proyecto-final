package com.proyecto_final.ms_credit.infrastructure.adapter.out.persistence.repository;

import com.proyecto_final.ms_credit.application.port.out.CreditRepositoryPort;
import com.proyecto_final.ms_credit.domain.model.Credit;
import com.proyecto_final.ms_credit.domain.model.CreditTypeEnum;
import com.proyecto_final.ms_credit.infrastructure.adapter.out.persistence.mapper.CreditPersistenceMapper;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Maybe;
import io.reactivex.rxjava3.core.Single;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * Implementa el puerto de salida de créditos sobre Spring Data MongoDB.
 */
@Component
@RequiredArgsConstructor
public class CreditRepositoryAdapter implements CreditRepositoryPort {

    private final CreditRepository creditRepository;

    private final CreditPersistenceMapper creditPersistenceMapper;

    /**
     * {@inheritDoc}
     */
    @Override
    public Single<Credit> save(Credit credit) {
        return creditRepository.save(creditPersistenceMapper.toEntity(credit))
                .map(creditPersistenceMapper::toDomain);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Maybe<Credit> findActiveById(String creditId) {
        return creditRepository.findByIdAndIsActiveTrue(creditId)
                .map(creditPersistenceMapper::toDomain);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Flowable<Credit> findAllActive() {
        return creditRepository.findByIsActiveTrue()
                .map(creditPersistenceMapper::toDomain);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Single<Long> countActiveByCustomerIdAndType(String customerId, CreditTypeEnum type) {
        return creditRepository.countByCustomerIdAndTypeAndIsActiveTrue(customerId, type);
    }
}