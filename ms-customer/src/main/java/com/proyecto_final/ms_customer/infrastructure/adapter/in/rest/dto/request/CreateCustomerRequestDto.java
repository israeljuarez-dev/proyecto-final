package com.proyecto_final.ms_customer.infrastructure.adapter.in.rest.dto.request;

import com.proyecto_final.ms_customer.domain.model.CustomerTypeEnum;
import com.proyecto_final.ms_customer.domain.model.DocumentTypeEnum;
import com.proyecto_final.ms_customer.infrastructure.adapter.in.rest.validation.CustomerValidationConstants;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Builder;

/**
 * Request para registrar un cliente.
 */
@Builder
public record CreateCustomerRequestDto(

        @NotNull(message = "type is required")
        CustomerTypeEnum type,

        @NotNull(message = "documentType is required")
        DocumentTypeEnum documentType,

        @NotBlank(message = "documentNumber is required")
        @Pattern(
                regexp = CustomerValidationConstants.DOCUMENT_NUMBER_REGEX,
                message = "documentNumber must be alphanumeric, 6 to 20 characters"
        )
        String documentNumber,

        @NotBlank(message = "legalName is required")
        @Size(
                max = CustomerValidationConstants.LEGAL_NAME_MAX_LENGTH,
                message = "legalName must have at most 150 characters"
        )
        String legalName,

        @NotBlank(message = "email is required")
        @Email(message = "email must be a valid email address")
        @Size(
                max = CustomerValidationConstants.EMAIL_MAX_LENGTH,
                message = "email must have at most 100 characters"
        )
        String email,

        @NotBlank(message = "phoneNumber is required")
        @Pattern(
                regexp = CustomerValidationConstants.PHONE_NUMBER_REGEX,
                message = "phoneNumber must have 9 to 15 digits"
        )
        String phoneNumber) {
}
