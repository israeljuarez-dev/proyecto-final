package com.proyecto_final.ms_account.application.port.in.uses_cases;

import com.proyecto_final.ms_account.application.port.in.dto.CreateAccountDto;
import com.proyecto_final.ms_account.domain.model.Account;
import io.reactivex.rxjava3.core.Single;

/**
 * Caso de uso: abrir una cuenta bancaria.
 */
public interface CreateAccountUseCase {

    /**
     * Abre una cuenta validando el cliente, el tipo de cuenta, sus condiciones y los límites.
     *
     * @param createAccountDto datos de la cuenta a abrir
     * @return la cuenta creada
     */
    Single<Account> create(CreateAccountDto createAccountDto);
}
