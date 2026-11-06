package com.proyecto_final.ms_customer.infrastructure.adapter.in.rest.mapper;

import com.proyecto_final.ms_customer.application.port.in.dto.CreateCustomerDto;
import com.proyecto_final.ms_customer.application.port.in.dto.UpdateCustomerDto;
import com.proyecto_final.ms_customer.domain.model.Customer;
import com.proyecto_final.ms_customer.infrastructure.adapter.in.rest.dto.request.CreateCustomerRequestDto;
import com.proyecto_final.ms_customer.infrastructure.adapter.in.rest.dto.request.UpdateCustomerRequestDto;
import com.proyecto_final.ms_customer.infrastructure.adapter.in.rest.dto.response.CustomerResponseDto;
import org.springframework.stereotype.Component;

/**
 * Mapeo manual entre los DTOs REST, los comandos de application y el modelo de dominio.
 */
@Component
public class CustomerRestMapper {

    /**
     * Convierte la solicitud de creación en el comando de application.
     */
    public CreateCustomerDto toCreateDto(CreateCustomerRequestDto request) {
        return new CreateCustomerDto(
                request.type(),
                request.documentType(),
                request.documentNumber(),
                request.legalName(),
                request.email(),
                request.phoneNumber());
    }

    /**
     * Convierte la solicitud de actualización y el id de la URL en el comando de application.
     */
    public UpdateCustomerDto toUpdateDto(String customerId, UpdateCustomerRequestDto request) {
        return new UpdateCustomerDto(
                customerId,
                request.legalName(),
                request.email(),
                request.phoneNumber());
    }

    /**
     * Convierte el cliente del dominio en el DTO de respuesta.
     */
    public CustomerResponseDto toResponse(Customer customer) {
        return new CustomerResponseDto(
                customer.getId(),
                customer.getType(),
                customer.getDocumentType(),
                customer.getDocumentNumber(),
                customer.getLegalName(),
                customer.getEmail(),
                customer.getPhoneNumber(),
                customer.getCreatedAt(),
                customer.getUpdatedAt());
    }
}
