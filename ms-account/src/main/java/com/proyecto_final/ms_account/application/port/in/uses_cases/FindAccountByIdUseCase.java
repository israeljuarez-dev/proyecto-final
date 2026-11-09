package com.proyecto_final.ms_account.application.port.in.uses_cases;

import com.proyecto_final.ms_account.domain.model.Account;
import io.reactivex.rxjava3.core.Single;

/**
 * Caso de uso: consultar una cuenta activa por su identificador.
 */
public interface FindAccountByIdUseCase {

    /**
     * Busca una cuenta activa por su identificador.
     *
     * @param accountId identificador de la cuenta
     * @return la cuenta, o error AccountNotFoundException si no existe
     */
    Single<Account> findById(String accountId);
}
