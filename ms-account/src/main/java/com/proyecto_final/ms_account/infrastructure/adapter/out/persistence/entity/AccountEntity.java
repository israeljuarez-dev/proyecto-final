package com.proyecto_final.ms_account.infrastructure.adapter.out.persistence.entity;

import com.proyecto_final.ms_account.domain.model.AccountTypeEnum;
import com.proyecto_final.ms_account.domain.model.CustomerTypeEnum;
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
import java.util.List;

/**
 * Documento de MongoDB que persiste una cuenta (colección "accounts").
 * Los nombres de los atributos coinciden con los de los campos en Mongo.
 */
@Document(collection = "accounts")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class AccountEntity {

    @Id
    @EqualsAndHashCode.Include
    private String id;

    private String customerId;

    private CustomerTypeEnum customerType;

    private AccountTypeEnum type;

    private Integer monthlyMovementLimit;

    @Field(targetType = FieldType.DECIMAL128)
    private BigDecimal maintenanceFee;

    private Integer movementDayOfMonth;

    private List<AccountPersonEntity> holders;

    private List<AccountPersonEntity> signers;

    private Boolean isActive;

    private Instant createdAt;

    private Instant updatedAt;
}