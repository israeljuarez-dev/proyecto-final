package com.proyecto_final.ms_account.infrastructure.adapter.out.persistence.mapper;

import com.proyecto_final.ms_account.domain.model.Account;
import com.proyecto_final.ms_account.domain.model.AccountPerson;
import com.proyecto_final.ms_account.infrastructure.adapter.out.persistence.entity.AccountEntity;
import com.proyecto_final.ms_account.infrastructure.adapter.out.persistence.entity.AccountPersonEntity;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Mapeo manual entre el modelo de dominio y la entidad de Mongo.
 */
@Component
public class AccountPersistenceMapper {

    /**
     * Convierte la cuenta del dominio en su documento de Mongo.
     */
    public AccountEntity toEntity(Account account) {
        return AccountEntity.builder()
                .id(account.getId())
                .customerId(account.getCustomerId())
                .customerType(account.getCustomerType())
                .type(account.getType())
                .monthlyMovementLimit(account.getMonthlyMovementLimit())
                .maintenanceFee(account.getMaintenanceFee())
                .movementDayOfMonth(account.getMovementDayOfMonth())
                .holders(toPersonEntities(account.getHolders()))
                .signers(toPersonEntities(account.getSigners()))
                .isActive(account.isActive())
                .createdAt(account.getCreatedAt())
                .updatedAt(account.getUpdatedAt())
                .build();
    }

    /**
     * Convierte el documento de Mongo en la cuenta del dominio.
     */
    public Account toDomain(AccountEntity entity) {
        return Account.builder()
                .id(entity.getId())
                .customerId(entity.getCustomerId())
                .customerType(entity.getCustomerType())
                .type(entity.getType())
                .monthlyMovementLimit(entity.getMonthlyMovementLimit())
                .maintenanceFee(entity.getMaintenanceFee())
                .movementDayOfMonth(entity.getMovementDayOfMonth())
                .holders(toPersons(entity.getHolders()))
                .signers(toPersons(entity.getSigners()))
                .isActive(entity.getIsActive())
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .build();
    }

    private List<AccountPersonEntity> toPersonEntities(List<AccountPerson> persons) {
        return persons.stream()
                .map(this::toPersonEntity)
                .toList();
    }

    private AccountPersonEntity toPersonEntity(AccountPerson person) {
        return AccountPersonEntity.builder()
                .documentType(person.documentType())
                .documentNumber(person.documentNumber())
                .fullName(person.fullName())
                .build();
    }

    private List<AccountPerson> toPersons(List<AccountPersonEntity> entities) {
        return entities.stream()
                .map(this::toPerson)
                .toList();
    }

    private AccountPerson toPerson(AccountPersonEntity entity) {
        return new AccountPerson(entity.getDocumentType(), entity.getDocumentNumber(), entity.getFullName());
    }
}
