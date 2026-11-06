package com.proyecto_final.ms_customer.application.port.in.use_cases;

import com.proyecto_final.ms_customer.application.port.in.dto.CreateCustomerDto;
import com.proyecto_final.ms_customer.domain.model.Customer;
import io.reactivex.rxjava3.core.Single;

/**
 * Caso de uso: registrar un cliente nuevo.
 */
public interface CreateCustomerUseCase {

    /**
     * Registra un cliente validando su tipo de documento y que no esté duplicado.
     *
     * @param createCustomerDto datos del cliente a registrar
     * @return el cliente registrado
     */
    Single<Customer> create(CreateCustomerDto createCustomerDto);
}
