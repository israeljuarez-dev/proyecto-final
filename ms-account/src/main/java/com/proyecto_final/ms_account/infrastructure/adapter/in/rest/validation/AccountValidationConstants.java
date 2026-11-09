package com.proyecto_final.ms_account.infrastructure.adapter.in.rest.validation;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * Constantes de validación compartidas por los DTOs de request (DRY).
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class AccountValidationConstants {

    // Alfanumérico de 6 a 20 caracteres: cubre DNI, CEX y pasaporte
    public static final String DOCUMENT_NUMBER_REGEX = "^[A-Za-z0-9]{6,20}$";

    public static final int FULL_NAME_MAX_LENGTH = 150;

    // Se limita a 28 para que el día exista en todos los meses
    public static final int MIN_MOVEMENT_DAY = 1;

    public static final int MAX_MOVEMENT_DAY = 28;
}