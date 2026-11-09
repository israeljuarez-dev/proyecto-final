package com.proyecto_final.ms_account.application.port.in.uses_cases;

import com.proyecto_final.ms_account.application.port.in.dto.UpdateAccountDto;
import com.proyecto_final.ms_account.domain.model.Account;
import io.reactivex.rxjava3.core.Single;

/**
 * Caso de uso: actualizar las condiciones, titulares y firmantes de una cuenta.
 */
public interface UpdateAccountUseCase {

    /**
     * Actualiza una cuenta activa validando que los datos sean coherentes con su tipo.
     *
     * @param updateAccountDto identificador de la cuenta y nuevos datos
     * @return la cuenta actualizada, o error AccountNotFoundException si no existe
     */
    Single<Account> update(UpdateAccountDto updateAccountDto);
}
