package com.proyecto_final.ms_customer.infrastructure.adapter.in.rest.dto.response;

import com.proyecto_final.ms_customer.domain.model.CustomerTypeEnum;
import com.proyecto_final.ms_customer.domain.model.DocumentTypeEnum;
import lombok.Builder;

import java.time.Instant;

@Builder
public record CustomerResponseDto(
        String id,
        CustomerTypeEnum type,
        DocumentTypeEnum documentType,
        String documentNumber,
        String legalName,
        String email,
        String phoneNumber,
        Instant createdAt,
        Instant updatedAt
) {
}
