package com.proyecto_final.ms_customer.domain.exception;

import com.proyecto_final.ms_customer.domain.model.CustomerTypeEnum;
import com.proyecto_final.ms_customer.domain.model.DocumentTypeEnum;
import lombok.Getter;

/**
 * Se lanza cuando el tipo de documento no es admitido por el tipo de cliente.
 */
@Getter
public class InvalidDocumentTypeException extends RuntimeException {

    private final CustomerTypeEnum customerType;
    private final DocumentTypeEnum documentType;

    public InvalidDocumentTypeException(CustomerTypeEnum customerType, DocumentTypeEnum documentType) {
        super("Document type " + documentType + " is not allowed for customer type " + customerType);
        this.customerType = customerType;
        this.documentType = documentType;
    }
}
