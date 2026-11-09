package com.proyecto_final.ms_account.application.port.out;

import com.proyecto_final.ms_account.domain.model.Account;
import com.proyecto_final.ms_account.domain.model.AccountTypeEnum;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Maybe;
import io.reactivex.rxjava3.core.Single;

/**
 * Puerto de salida: operaciones de persistencia de cuentas que necesita la aplicación.
 */
public interface AccountRepositoryPort {

    /**
     * Guarda una cuenta nueva o actualiza una existente.
     *
     * @param account cuenta a guardar
     * @return la cuenta guardada
     */
    Single<Account> save(Account account);

    /**
     * Busca una cuenta activa por su identificador.
     *
     * @param accountId identificador de la cuenta
     * @return la cuenta, o vacío si no existe o está inactiva
     */
    Maybe<Account> findActiveById(String accountId);

    /**
     * Obtiene todas las cuentas activas.
     *
     * @return flujo con las cuentas activas
     */
    Flowable<Account> findAllActive();

    /**
     * Cuenta las cuentas activas de un tipo que tiene un cliente.
     *
     * @param customerId identificador del cliente
     * @param type       tipo de cuenta
     * @return cantidad de cuentas activas de ese tipo
     */
    Single<Long> countActiveByCustomerIdAndType(String customerId, AccountTypeEnum type);
}