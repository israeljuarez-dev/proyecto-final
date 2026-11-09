package com.proyecto_final.ms_credit.infrastructure.adapter.out.persistence.entity;

import com.proyecto_final.ms_credit.domain.model.CreditTypeEnum;
import com.proyecto_final.ms_credit.domain.model.CustomerTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;

import java.math.BigDecimal;
import java.time.Instant;

/**
 * Documento de MongoDB que persiste un crédito (colección "credits").
 * Los nombres de los atributos coinciden con los de los campos en Mongo.
 */
@Document(collection = "credits")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class CreditEntity {

    @Id
    @EqualsAndHashCode.Include
    private String id;

    private String customerId;

    private CustomerTypeEnum customerType;

    private CreditTypeEnum type;

    @Field(targetType = FieldType.DECIMAL128)
    private BigDecimal approvedAmount;

    @Field(targetType = FieldType.DECIMAL128)
    private BigDecimal creditLimit;

    private Boolean isActive;

    private Instant createdAt;

    private Instant updatedAt;
}
