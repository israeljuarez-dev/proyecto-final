package com.proyecto_final.ms_customer.application.port.in.use_cases;

import com.proyecto_final.ms_customer.application.port.in.dto.UpdateCustomerDto;
import com.proyecto_final.ms_customer.domain.model.Customer;
import io.reactivex.rxjava3.core.Single;

/**
 * Caso de uso: actualizar los datos de contacto de un cliente.
 */
public interface UpdateCustomerUseCase {

    /**
     * Actualiza nombre, correo y celular de un cliente activo.
     *
     * @param updateCustomerDto del cliente y nuevos datos de contacto
     * @return el cliente actualizado, o error CustomerNotFoundException si no existe
     */
    Single<Customer> update(UpdateCustomerDto updateCustomerDto);
}
