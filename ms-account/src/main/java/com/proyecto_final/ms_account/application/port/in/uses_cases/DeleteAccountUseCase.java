package com.proyecto_final.ms_account.application.port.in.uses_cases;

import io.reactivex.rxjava3.core.Completable;

/**
 * Caso de uso: eliminar lógicamente una cuenta.
 */
public interface DeleteAccountUseCase {

    /**
     * Marca una cuenta activa como inactiva (soft delete).
     *
     * @param accountId identificador de la cuenta
     * @return completa si se eliminó, o error AccountNotFoundException si no existe
     */
    Completable delete(String accountId);
}