package com.proyecto_final.ms_customer.infrastructure.adapter.out.persistence.mapper;

import com.proyecto_final.ms_customer.domain.model.Customer;
import com.proyecto_final.ms_customer.infrastructure.adapter.out.persistence.entity.CustomerEntity;
import org.springframework.stereotype.Component;

/**
 * Mapeo manual entre el modelo de dominio y la entidad de Mongo.
 */
@Component
public class CustomerPersistenceMapper {

    /**
     * Convierte el cliente del dominio en su documento de Mongo.
     */
    public CustomerEntity toEntity(Customer customer) {
        return CustomerEntity.builder()
                .id(customer.getId())
                .type(customer.getType())
                .documentType(customer.getDocumentType())
                .documentNumber(customer.getDocumentNumber())
                .legalName(customer.getLegalName())
                .email(customer.getEmail())
                .phoneNumber(customer.getPhoneNumber())
                .isActive(customer.isActive())
                .createdAt(customer.getCreatedAt())
                .updatedAt(customer.getUpdatedAt())
                .build();
    }

    /**
     * Convierte el documento de Mongo en el cliente del dominio.
     */
    public Customer toDomain(CustomerEntity entity) {
        return Customer.builder()
                .id(entity.getId())
                .type(entity.getType())
                .documentType(entity.getDocumentType())
                .documentNumber(entity.getDocumentNumber())
                .legalName(entity.getLegalName())
                .email(entity.getEmail())
                .phoneNumber(entity.getPhoneNumber())
                .isActive(entity.getIsActive())
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .build();
    }
}
