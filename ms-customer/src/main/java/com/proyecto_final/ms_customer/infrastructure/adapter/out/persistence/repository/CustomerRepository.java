package com.proyecto_final.ms_customer.infrastructure.adapter.out.persistence.repository;

import com.proyecto_final.ms_customer.domain.model.DocumentTypeEnum;
import com.proyecto_final.ms_customer.infrastructure.adapter.out.persistence.entity.CustomerEntity;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Maybe;
import io.reactivex.rxjava3.core.Single;
import org.springframework.data.repository.reactive.RxJava3CrudRepository;

/**
 * Repositorio Spring Data con tipos de RxJava 3.
 */
public interface CustomerRepository extends RxJava3CrudRepository<CustomerEntity, String> {

    Maybe<CustomerEntity> findByIdAndIsActiveTrue(String id);

    Flowable<CustomerEntity> findByIsActiveTrue();

    Single<Boolean> existsByDocumentTypeAndDocumentNumberAndIsActiveTrue(
            DocumentTypeEnum documentType, String documentNumber
    );
}
