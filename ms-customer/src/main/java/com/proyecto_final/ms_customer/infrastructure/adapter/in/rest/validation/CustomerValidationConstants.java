package com.proyecto_final.ms_customer.infrastructure.adapter.in.rest.validation;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * Constantes de validación compartidas por los DTOs de request.
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class CustomerValidationConstants {

    // Alfanumérico de 6 a 20 caracteres: cubre DNI, CEX, pasaporte y RUC
    public static final String DOCUMENT_NUMBER_REGEX = "^[A-Za-z0-9]{6,20}$";

    // Celular de 9 a 15 dígitos, con prefijo internacional opcional
    public static final String PHONE_NUMBER_REGEX = "^\\+?[0-9]{9,15}$";

    public static final int LEGAL_NAME_MAX_LENGTH = 150;

    public static final int EMAIL_MAX_LENGTH = 100;
}
