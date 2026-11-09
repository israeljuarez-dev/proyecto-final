package com.proyecto_final.ms_credit.infrastructure.adapter.in.rest.mappper;

import com.proyecto_final.ms_credit.application.port.in.dto.CreateCreditDto;
import com.proyecto_final.ms_credit.application.port.in.dto.UpdateCreditDto;
import com.proyecto_final.ms_credit.domain.model.Credit;
import com.proyecto_final.ms_credit.infrastructure.adapter.in.rest.dto.request.CreateCreditRequestDto;
import com.proyecto_final.ms_credit.infrastructure.adapter.in.rest.dto.request.UpdateCreditRequestDto;
import com.proyecto_final.ms_credit.infrastructure.adapter.in.rest.dto.response.CreditResponseDto;
import org.springframework.stereotype.Component;

/**
 * Mapeo manual entre los DTOs REST, los DTOs de application y el modelo de dominio.
 */
@Component
public class CreditRestMapper {

    /**
     * Convierte la solicitud de creación en el DTO de application.
     */
    public CreateCreditDto toCreateDto(CreateCreditRequestDto request) {
        return CreateCreditDto.builder()
                .customerId(request.customerId())
                .type(request.type())
                .approvedAmount(request.approvedAmount())
                .creditLimit(request.creditLimit())
                .build();
    }

    /**
     * Convierte la solicitud de actualización y el id de la URL en el DTO de application.
     */
    public UpdateCreditDto toUpdateDto(String creditId, UpdateCreditRequestDto request) {
        return UpdateCreditDto.builder()
                .creditId(creditId)
                .approvedAmount(request.approvedAmount())
                .creditLimit(request.creditLimit())
                .build();
    }

    /**
     * Convierte el crédito del dominio en el DTO de respuesta.
     */
    public CreditResponseDto toResponse(Credit credit) {
        return CreditResponseDto.builder()
                .id(credit.getId())
                .customerId(credit.getCustomerId())
                .customerType(credit.getCustomerType())
                .type(credit.getType())
                .approvedAmount(credit.getApprovedAmount())
                .creditLimit(credit.getCreditLimit())
                .createdAt(credit.getCreatedAt())
                .updatedAt(credit.getUpdatedAt())
                .build();
    }
}
