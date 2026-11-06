package com.proyecto_final.ms_customer.application.port.in.use_cases;

import com.proyecto_final.ms_customer.domain.model.Customer;
import io.reactivex.rxjava3.core.Flowable;

/**
 * Caso de uso: listar los clientes activos.
 */
public interface FindAllCustomersUseCase {

    /**
     * Obtiene todos los clientes activos.
     *
     * @return flujo con los clientes activos (vacío si no hay ninguno)
     */
    Flowable<Customer> findAll();
}
