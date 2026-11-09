package com.proyecto_final.ms_credit.infrastructure.adapter.out.persistence.repository;

import com.proyecto_final.ms_credit.domain.model.CreditTypeEnum;
import com.proyecto_final.ms_credit.infrastructure.adapter.out.persistence.entity.CreditEntity;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Maybe;
import io.reactivex.rxjava3.core.Single;
import org.springframework.data.repository.reactive.RxJava3CrudRepository;

/**
 * Repositorio Spring Data con tipos de RxJava 3. Las consultas se derivan del nombre
 */
public interface CreditRepository extends RxJava3CrudRepository<CreditEntity, String> {

    Maybe<CreditEntity> findByIdAndIsActiveTrue(String id);

    Flowable<CreditEntity> findByIsActiveTrue();

    Single<Long> countByCustomerIdAndTypeAndIsActiveTrue(String customerId, CreditTypeEnum type);
}
