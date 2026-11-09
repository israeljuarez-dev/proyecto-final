package com.proyecto_final.ms_credit.infrastructure.adapter.out.client.customer.dto;

import com.proyecto_final.ms_credit.domain.model.CustomerTypeEnum;

/**
 * Parte de la respuesta de ms-customer que interesa a ms-credit. Los demás campos se ignoran.
 */
public record CustomerClientResponseDto(
        String id,
        CustomerTypeEnum type
) {
}
