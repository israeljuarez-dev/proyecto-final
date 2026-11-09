package com.proyecto_final.ms_credit.infrastructure.adapter.out.persistence.mapper;

import com.proyecto_final.ms_credit.domain.model.Credit;
import com.proyecto_final.ms_credit.infrastructure.adapter.out.persistence.entity.CreditEntity;
import org.springframework.stereotype.Component;

/**
 * Mapeo manual entre el modelo de dominio y la entidad de Mongo.
 */
@Component
public class CreditPersistenceMapper {

    /**
     * Convierte el crédito del dominio en su documento de Mongo.
     */
    public CreditEntity toEntity(Credit credit) {
        return CreditEntity.builder()
                .id(credit.getId())
                .customerId(credit.getCustomerId())
                .customerType(credit.getCustomerType())
                .type(credit.getType())
                .approvedAmount(credit.getApprovedAmount())
                .creditLimit(credit.getCreditLimit())
                .isActive(credit.isActive())
                .createdAt(credit.getCreatedAt())
                .updatedAt(credit.getUpdatedAt())
                .build();
    }

    /**
     * Convierte el documento de Mongo en el crédito del dominio.
     */
    public Credit toDomain(CreditEntity entity) {
        return Credit.builder()
                .id(entity.getId())
                .customerId(entity.getCustomerId())
                .customerType(entity.getCustomerType())
                .type(entity.getType())
                .approvedAmount(entity.getApprovedAmount())
                .creditLimit(entity.getCreditLimit())
                .isActive(entity.getIsActive())
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .build();
    }
}
