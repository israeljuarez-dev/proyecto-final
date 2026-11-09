package com.proyecto_final.ms_account.application.port.out.dto;

import com.proyecto_final.ms_account.domain.model.CustomerTypeEnum;

/**
 * Datos del cliente que ms-account necesita conocer: su identificador y su tipo.
 */
public record CustomerInfoDto(
        String id,
        CustomerTypeEnum type
) {
}
