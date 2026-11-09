package com.proyecto_final.ms_account.infrastructure.adapter.out.persistence.repository;

import com.proyecto_final.ms_account.application.port.out.AccountRepositoryPort;
import com.proyecto_final.ms_account.domain.model.Account;
import com.proyecto_final.ms_account.domain.model.AccountTypeEnum;
import com.proyecto_final.ms_account.infrastructure.adapter.out.persistence.mapper.AccountPersistenceMapper;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Maybe;
import io.reactivex.rxjava3.core.Single;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * Implementa el puerto de salida de cuentas sobre Spring Data MongoDB.
 */
@Component
@RequiredArgsConstructor
public class AccountRepositoryAdapter implements AccountRepositoryPort {

    private final AccountRepository accountRepository;

    private final AccountPersistenceMapper accountPersistenceMapper;

    /**
     * {@inheritDoc}
     */
    @Override
    public Single<Account> save(Account account) {
        return accountRepository.save(accountPersistenceMapper.toEntity(account))
                .map(accountPersistenceMapper::toDomain);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Maybe<Account> findActiveById(String accountId) {
        return accountRepository.findByIdAndIsActiveTrue(accountId)
                .map(accountPersistenceMapper::toDomain);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Flowable<Account> findAllActive() {
        return accountRepository.findByIsActiveTrue()
                .map(accountPersistenceMapper::toDomain);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Single<Long> countActiveByCustomerIdAndType(String customerId, AccountTypeEnum type) {
        return accountRepository.countByCustomerIdAndTypeAndIsActiveTrue(customerId, type);
    }
}