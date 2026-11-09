package com.proyecto_final.ms_account.infrastructure.adapter.in.rest.dto.request;

import com.proyecto_final.ms_account.domain.model.DocumentTypeEnum;
import com.proyecto_final.ms_account.infrastructure.adapter.in.rest.validation.AccountValidationConstants;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Builder;

/**
 * Titular o firmante recibido en la solicitud de una cuenta.
 */
@Builder
public record AccountPersonRequestDto(

        @NotNull(message = "documentType is required")
        DocumentTypeEnum documentType,

        @NotBlank(message = "documentNumber is required")
        @Pattern(
                regexp = AccountValidationConstants.DOCUMENT_NUMBER_REGEX,
                message = "documentNumber must be alphanumeric, 6 to 20 characters"
        )
        String documentNumber,

        @NotBlank(message = "fullName is required")
        @Size(
                max = AccountValidationConstants.FULL_NAME_MAX_LENGTH,
                message = "fullName must have at most 150 characters"
        )
        String fullName) {
}
