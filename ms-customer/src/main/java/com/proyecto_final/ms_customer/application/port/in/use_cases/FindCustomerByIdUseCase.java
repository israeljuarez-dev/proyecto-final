package com.proyecto_final.ms_customer.application.port.in.use_cases;

import com.proyecto_final.ms_customer.domain.model.Customer;
import io.reactivex.rxjava3.core.Single;

/**
 * Caso de uso: consultar un cliente activo por su identificador.
 */
public interface FindCustomerByIdUseCase {
    /**
     * Busca un cliente activo por su identificador.
     *
     * @param customerId identificador del cliente
     * @return el cliente encontrado, o error CustomerNotFoundException si no existe
     */
    Single<Customer> findById(String customerId);
}