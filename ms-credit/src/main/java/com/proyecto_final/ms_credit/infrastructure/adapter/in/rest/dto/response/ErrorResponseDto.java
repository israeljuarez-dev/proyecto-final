package com.proyecto_final.ms_credit.infrastructure.adapter.in.rest.dto.response;

import lombok.Builder;

import java.time.Instant;
import java.util.List;

/**
 * Respuesta estándar de error de la API.
 */
@Builder
public record ErrorResponseDto(
        Instant timestamp,
        int status,
        String error,
        String message,
        List<String> details
) {
}
