package com.proyecto_final.ms_account.infrastructure.adapter.in.rest.mapper;

import com.proyecto_final.ms_account.application.port.in.dto.CreateAccountDto;
import com.proyecto_final.ms_account.application.port.in.dto.UpdateAccountDto;
import com.proyecto_final.ms_account.domain.model.Account;
import com.proyecto_final.ms_account.domain.model.AccountPerson;
import com.proyecto_final.ms_account.infrastructure.adapter.in.rest.dto.request.AccountPersonRequestDto;
import com.proyecto_final.ms_account.infrastructure.adapter.in.rest.dto.request.CreateAccountRequestDto;
import com.proyecto_final.ms_account.infrastructure.adapter.in.rest.dto.request.UpdateAccountRequestDto;
import com.proyecto_final.ms_account.infrastructure.adapter.in.rest.dto.response.AccountPersonResponseDto;
import com.proyecto_final.ms_account.infrastructure.adapter.in.rest.dto.response.AccountResponseDto;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Mapeo manual entre los DTOs REST, los DTOs de application y el modelo de dominio.
 */
@Component
public class AccountRestMapper {

    /**
     * Convierte la solicitud de creación en el DTO de application.
     */
    public CreateAccountDto toCreateDto(CreateAccountRequestDto request) {
        return CreateAccountDto.builder()
                .customerId(request.customerId())
                .type(request.type())
                .monthlyMovementLimit(request.monthlyMovementLimit())
                .maintenanceFee(request.maintenanceFee())
                .movementDayOfMonth(request.movementDayOfMonth())
                .holders(toPersons(request.holders()))
                .signers(toPersons(request.signers()))
                .build();
    }

    /**
     * Convierte la solicitud de actualización y el id de la URL en el DTO de application.
     */
    public UpdateAccountDto toUpdateDto(String accountId, UpdateAccountRequestDto request) {
        return UpdateAccountDto.builder()
                .accountId(accountId)
                .monthlyMovementLimit(request.monthlyMovementLimit())
                .maintenanceFee(request.maintenanceFee())
                .movementDayOfMonth(request.movementDayOfMonth())
                .holders(toPersons(request.holders()))
                .signers(toPersons(request.signers()))
                .build();
    }

    /**
     * Convierte la cuenta del dominio en el DTO de respuesta.
     */
    public AccountResponseDto toResponse(Account account) {
        return AccountResponseDto.builder()
                .id(account.getId())
                .customerId(account.getCustomerId())
                .customerType(account.getCustomerType())
                .type(account.getType())
                .monthlyMovementLimit(account.getMonthlyMovementLimit())
                .maintenanceFee(account.getMaintenanceFee())
                .movementDayOfMonth(account.getMovementDayOfMonth())
                .holders(toPersonResponses(account.getHolders()))
                .signers(toPersonResponses(account.getSigners()))
                .createdAt(account.getCreatedAt())
                .updatedAt(account.getUpdatedAt())
                .build();
    }

    // Una lista ausente en el JSON se trata como lista vacía
    private List<AccountPerson> toPersons(List<AccountPersonRequestDto> persons) {
        if (persons == null) {
            return List.of();
        }

        return persons.stream()
                .map(this::toPerson)
                .toList();
    }

    private AccountPerson toPerson(AccountPersonRequestDto person) {
        return new AccountPerson(person.documentType(), person.documentNumber(), person.fullName());
    }

    private List<AccountPersonResponseDto> toPersonResponses(List<AccountPerson> persons) {
        return persons.stream()
                .map(this::toPersonResponse)
                .toList();
    }

    private AccountPersonResponseDto toPersonResponse(AccountPerson person) {
        return AccountPersonResponseDto.builder()
                .documentType(person.documentType())
                .documentNumber(person.documentNumber())
                .fullName(person.fullName())
                .build();
    }
}
