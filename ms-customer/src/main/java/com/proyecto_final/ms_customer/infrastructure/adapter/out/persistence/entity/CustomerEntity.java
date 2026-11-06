package com.proyecto_final.ms_customer.infrastructure.adapter.out.persistence.entity;

import java.time.Instant;

import com.proyecto_final.ms_customer.domain.model.CustomerTypeEnum;
import com.proyecto_final.ms_customer.domain.model.DocumentTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Documento de MongoDB que persiste un cliente (colección "customers").
 * Los nombres de los atributos coinciden con los de los campos en Mongo,
 * por eso no hace falta @Field en ninguno.
 */
@Document(collection = "customers")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class CustomerEntity {

    @Id
    private String id;

    private CustomerTypeEnum type;

    @EqualsAndHashCode.Include
    private DocumentTypeEnum documentType;

    @EqualsAndHashCode.Include
    private String documentNumber;

    private String legalName;
    private String email;
    private String phoneNumber;

    private Boolean isActive;

    private Instant createdAt;
    private Instant updatedAt;
}
