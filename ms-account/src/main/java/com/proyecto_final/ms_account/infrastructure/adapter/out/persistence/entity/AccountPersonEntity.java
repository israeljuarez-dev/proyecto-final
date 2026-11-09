package com.proyecto_final.ms_account.infrastructure.adapter.out.persistence.entity;

import com.proyecto_final.ms_account.domain.model.DocumentTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * Titular o firmante embebido dentro del documento de la cuenta.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@EqualsAndHashCode
public class AccountPersonEntity {

    private DocumentTypeEnum documentType;

    private String documentNumber;

    private String fullName;
}
