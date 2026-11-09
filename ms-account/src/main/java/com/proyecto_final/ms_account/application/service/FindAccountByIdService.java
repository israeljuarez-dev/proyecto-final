package com.proyecto_final.ms_account.application.service;

import com.proyecto_final.ms_account.application.port.in.uses_cases.FindAccountByIdUseCase;
import com.proyecto_final.ms_account.application.port.out.AccountRepositoryPort;
import com.proyecto_final.ms_account.domain.exception.AccountNotFoundException;
import com.proyecto_final.ms_account.domain.model.Account;
import io.reactivex.rxjava3.core.Single;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * Implementa el caso de uso FindAccountByIdUseCase de la cuenta: orquesta las reglas del negocio.
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class FindAccountByIdService implements FindAccountByIdUseCase {

    private final AccountRepositoryPort accountRepositoryPort;

    /**
     * {@inheritDoc}
     */
    @Override
    public Single<Account> findById(String accountId) {
        return accountRepositoryPort.findActiveById(accountId)
                .switchIfEmpty(Single.defer(() -> {
                    log.warn("Account not found, id {}", accountId);
                    return Single.error(new AccountNotFoundException(accountId));
                }));
    }
}
