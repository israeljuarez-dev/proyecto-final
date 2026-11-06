package com.proyecto_final.ms_customer.infrastructure.adapter.in.rest.dto.response;

import lombok.Builder;

import java.time.Instant;
import java.util.List;

@Builder
public record ErrorResponseDto(
        Instant timestamp,
        int status,
        String error,
        String message,
        List<String> details
) {
}
