package com.proyecto_final.ms_account.infrastructure.adapter.out.client.dto;

import com.proyecto_final.ms_account.domain.model.CustomerTypeEnum;

/**
 * Parte de la respuesta de ms-customer que interesa a ms-account. Los demás campos se ignoran.
 */
public record CustomerClientResponseDto(
        String id,
        CustomerTypeEnum type
) {
}