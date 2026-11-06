package com.proyecto_final.ms_customer.application.port.in.dto;

import com.proyecto_final.ms_customer.domain.model.CustomerTypeEnum;
import com.proyecto_final.ms_customer.domain.model.DocumentTypeEnum;
import lombok.Builder;

/**
 * Datos necesarios para registrar un cliente nuevo.
 */
@Builder
public record CreateCustomerDto(
        CustomerTypeEnum type,
        DocumentTypeEnum documentType,
        String documentNumber,
        String legalName,
        String email,
        String phoneNumber
) {
}
