package com.proyecto_final.ms_credit.application.port.out.client.customer;

import com.proyecto_final.ms_credit.application.port.out.dto.CustomerInfoDto;
import io.reactivex.rxjava3.core.Single;

/**
 * Puerto de salida: consulta de clientes en ms-customer.
 */
public interface CustomerClientPort {

    /**
     * Obtiene los datos básicos de un cliente.
     *
     * @param customerId identificador del cliente
     * @return el cliente, o error CustomerNotFoundException si no existe,
     * o CustomerServiceUnavailableException si ms-customer no está disponible
     */
    Single<CustomerInfoDto> findCustomerById(String customerId);
}
