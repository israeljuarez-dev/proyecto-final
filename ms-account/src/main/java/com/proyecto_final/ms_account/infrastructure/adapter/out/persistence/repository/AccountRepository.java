package com.proyecto_final.ms_account.infrastructure.adapter.out.persistence.repository;

import com.proyecto_final.ms_account.domain.model.AccountTypeEnum;
import com.proyecto_final.ms_account.infrastructure.adapter.out.persistence.entity.AccountEntity;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Maybe;
import io.reactivex.rxjava3.core.Single;
import org.springframework.data.repository.reactive.RxJava3CrudRepository;

/**
 * Repositorio Spring Data con tipos de RxJava 3. Las consultas se derivan del nombre
 * del método (sin @Query): "IsActiveTrue" filtra por isActive = true.
 */
public interface AccountRepository extends RxJava3CrudRepository<AccountEntity, String> {

    Maybe<AccountEntity> findByIdAndIsActiveTrue(String id);

    Flowable<AccountEntity> findByIsActiveTrue();

    Single<Long> countByCustomerIdAndTypeAndIsActiveTrue(String customerId, AccountTypeEnum type);
}
