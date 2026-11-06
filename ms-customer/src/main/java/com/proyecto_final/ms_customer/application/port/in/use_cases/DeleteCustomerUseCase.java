package com.proyecto_final.ms_customer.application.port.in.use_cases;

import io.reactivex.rxjava3.core.Completable;

/**
 * Caso de uso: eliminar lógicamente un cliente.
 */
public interface DeleteCustomerUseCase {

    /**
     * Marca un cliente activo como inactivo (soft delete).
     *
     * @param customerId identificador del cliente
     * @return completa si se eliminó, o error CustomerNotFoundException si no existe
     */
    Completable delete(String customerId);
}
