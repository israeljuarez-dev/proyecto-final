package com.proyecto_final.ms_customer.infrastructure.adapter.in.rest.dto.request;

import com.proyecto_final.ms_customer.infrastructure.adapter.in.rest.validation.CustomerValidationConstants;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Builder;

/**
 * Solicitud para actualizar los datos de contacto de un cliente.
 */
@Builder
public record UpdateCustomerRequestDto(

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
