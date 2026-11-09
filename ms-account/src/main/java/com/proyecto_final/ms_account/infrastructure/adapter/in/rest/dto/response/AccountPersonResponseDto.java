package com.proyecto_final.ms_account.infrastructure.adapter.in.rest.dto.response;

import com.proyecto_final.ms_account.domain.model.DocumentTypeEnum;
import lombok.Builder;

/**
 * Titular o firmante devuelto en la respuesta de una cuenta.
 */
@Builder
public record AccountPersonResponseDto(
        DocumentTypeEnum documentType,
        String documentNumber,
        String fullName
) {
}
