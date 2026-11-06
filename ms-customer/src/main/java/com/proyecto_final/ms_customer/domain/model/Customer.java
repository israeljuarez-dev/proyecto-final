package com.proyecto_final.ms_customer.domain.model;

import java.time.Instant;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

/**
 * Cliente del banco (personal o empresarial). Modelo de dominio inmutable:
 * cualquier cambio produce una nueva instancia mediante {@link #toBuilder()}.
 */
@Getter
@Builder(toBuilder = true)
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Customer {

    private final String id;

    private final CustomerTypeEnum type;

    @EqualsAndHashCode.Include
    private final DocumentTypeEnum documentType;

    @EqualsAndHashCode.Include
    private final String documentNumber;

    // Nombre completo (cliente personal) o razón social (cliente empresarial)
    private final String legalName;
    private final String email;
    private final String phoneNumber;

    // Soft delete: un cliente eliminado se conserva con isActive = false
    private final boolean isActive;

    private final Instant createdAt;
    private final Instant updatedAt;

    /**
     * Devuelve una copia del cliente con los datos de contacto actualizados.
     * El tipo y el documento no se modifican porque forman la identidad del cliente.
     *
     * @param legalName        nuevo nombre o razón social
     * @param email       nuevo correo electrónico
     * @param phoneNumber nuevo número de celular
     * @param now         instante de la actualización
     * @return copia actualizada del cliente
     */
    public Customer update(String legalName, String email, String phoneNumber, Instant now) {
        return toBuilder()
                .legalName(legalName)
                .email(email)
                .phoneNumber(phoneNumber)
                .updatedAt(now)
                .build();
    }

    /**
     * Devuelve una copia del cliente marcada como inactiva (eliminación lógica).
     *
     * @param now instante de la eliminación
     * @return copia inactiva del cliente
     */
    public Customer deactivate(Instant now) {
        return toBuilder()
                .isActive(false)
                .updatedAt(now)
                .build();
    }
}
