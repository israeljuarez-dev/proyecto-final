package com.proyecto_final.ms_customer.domain.model;

import lombok.RequiredArgsConstructor;

import java.util.Set;

/**
 * Tipos de cliente del banco. Cada tipo define qué documentos de identificación admite.
 */
@RequiredArgsConstructor
public enum CustomerTypeEnum {

    PERSONAL(Set.of(DocumentTypeEnum.DNI, DocumentTypeEnum.CEX, DocumentTypeEnum.PASSPORT)),
    BUSINESS(Set.of(DocumentTypeEnum.RUC));

    private final Set<DocumentTypeEnum> allowedDocumentTypes;

    /**
     * Indica si este tipo de cliente admite el tipo de documento recibido.
     *
     * @param documentType tipo de documento a verificar
     * @return true si el documento es válido para este tipo de cliente
     */
    public boolean allows(DocumentTypeEnum documentType) {
        return allowedDocumentTypes.contains(documentType);
    }
}
