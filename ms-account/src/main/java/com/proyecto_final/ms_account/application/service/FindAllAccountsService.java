package com.proyecto_final.ms_account.application.service;

import com.proyecto_final.ms_account.application.port.in.uses_cases.FindAllAccountsUseCase;
import com.proyecto_final.ms_account.application.port.out.AccountRepositoryPort;
import com.proyecto_final.ms_account.domain.model.Account;
import io.reactivex.rxjava3.core.Flowable;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * Implementa el caso de uso FindAllAccountsUseCase de la cuenta: orquesta las reglas del negocio.
 */
@Service
@RequiredArgsConstructor
public class FindAllAccountsService implements FindAllAccountsUseCase {

    private final AccountRepositoryPort accountRepositoryPort;

    /**
     * {@inheritDoc}
     */
    @Override
    public Flowable<Account> findAll() {
        return accountRepositoryPort.findAllActive();
    }
}
