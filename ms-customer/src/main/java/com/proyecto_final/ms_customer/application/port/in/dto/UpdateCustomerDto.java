package com.proyecto_final.ms_customer.application.port.in.dto;

import lombok.Builder;

/**
 * Datos de contacto que se pueden modificar de un cliente existente.
 */
@Builder
public record UpdateCustomerDto(
        String customerId,
        String legalName,
        String email,
        String phoneNumber
) {
}
