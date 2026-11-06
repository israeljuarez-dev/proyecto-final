package com.proyecto_final.ms_customer.domain.exception;

import com.proyecto_final.ms_customer.domain.model.DocumentTypeEnum;
import lombok.Getter;

/**
 * Se lanza cuando ya existe un cliente activo con el mismo tipo y número de documento.
 */
@Getter
public class DuplicateCustomerException extends RuntimeException {

    private final DocumentTypeEnum documentType;
    private final String documentNumber;

    public DuplicateCustomerException(DocumentTypeEnum documentType, String documentNumber) {
        super("Customer already exists with document " + documentType + " " + documentNumber);
        this.documentType = documentType;
        this.documentNumber = documentNumber;
    }
}
