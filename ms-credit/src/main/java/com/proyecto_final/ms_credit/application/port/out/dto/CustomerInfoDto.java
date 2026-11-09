package com.proyecto_final.ms_credit.application.port.out.dto;

import com.proyecto_final.ms_credit.domain.model.CustomerTypeEnum;

/**
 * Datos del cliente que ms-credit necesita conocer: su identificador y su tipo.
 */
public record CustomerInfoDto(
        String id,
        CustomerTypeEnum type
) {
}