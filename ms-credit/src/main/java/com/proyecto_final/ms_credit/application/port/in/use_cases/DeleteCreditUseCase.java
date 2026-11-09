package com.proyecto_final.ms_credit.application.port.in.use_cases;

import io.reactivex.rxjava3.core.Completable;

/**
 * Caso de uso: eliminar lógicamente un crédito.
 */
public interface DeleteCreditUseCase {

    /**
     * Marca un crédito activo como inactivo (soft delete).
     *
     * @param creditId identificador del crédito
     * @return completa si se eliminó, o error CreditNotFoundException si no existe
     */
    Completable delete(String creditId);
}