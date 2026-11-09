package com.proyecto_final.ms_account.domain.model;

/**
 * Persona asociada a una cuenta empresarial, como titular o como firmante autorizado.
 * Objeto de valor inmutable: dos personas son iguales si todos sus datos coinciden.
 */
public record AccountPerson(
        DocumentTypeEnum documentType,
        String documentNumber,
        String fullName
) {
}
