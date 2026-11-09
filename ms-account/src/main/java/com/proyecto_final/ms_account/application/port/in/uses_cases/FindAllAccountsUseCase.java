package com.proyecto_final.ms_account.application.port.in.uses_cases;

import com.proyecto_final.ms_account.domain.model.Account;
import io.reactivex.rxjava3.core.Flowable;

/**
 * Caso de uso: listar las cuentas activas.
 */
public interface FindAllAccountsUseCase {

    /**
     * Obtiene todas las cuentas activas.
     *
     * @return flujo con las cuentas activas (vacío si no hay ninguna)
     */
    Flowable<Account> findAll();
}
