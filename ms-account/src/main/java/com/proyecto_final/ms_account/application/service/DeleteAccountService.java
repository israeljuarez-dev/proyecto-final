package com.proyecto_final.ms_account.application.service;

import com.proyecto_final.ms_account.application.port.in.uses_cases.DeleteAccountUseCase;
import com.proyecto_final.ms_account.application.port.in.uses_cases.FindAccountByIdUseCase;
import com.proyecto_final.ms_account.application.port.out.AccountRepositoryPort;
import io.reactivex.rxjava3.core.Completable;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Clock;

/**
 * Implementa el caso de uso DeleteAccountUseCase de la cuenta: orquesta las reglas del negocio.
 */
@Service
@RequiredArgsConstructor
public class DeleteAccountService implements DeleteAccountUseCase {

    private final FindAccountByIdUseCase findAccountByIdUseCase;

    private final AccountRepositoryPort accountRepositoryPort;

    private final Clock clock;

    /**
     * {@inheritDoc}
     */
    @Override
    public Completable delete(String accountId) {
        return findAccountByIdUseCase.findById(accountId)
                .map(account -> account.deactivate(clock.instant()))
                .flatMap(accountRepositoryPort::save)
                .ignoreElement();
    }
}
