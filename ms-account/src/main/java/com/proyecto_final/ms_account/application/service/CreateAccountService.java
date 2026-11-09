package com.proyecto_final.ms_account.application.service;

import com.proyecto_final.ms_account.application.port.in.dto.CreateAccountDto;
import com.proyecto_final.ms_account.application.port.in.uses_cases.CreateAccountUseCase;
import com.proyecto_final.ms_account.application.port.out.AccountRepositoryPort;
import com.proyecto_final.ms_account.application.port.out.client.CustomerClientPort;
import com.proyecto_final.ms_account.application.port.out.dto.CustomerInfoDto;
import com.proyecto_final.ms_account.application.validator.AccountRulesValidator;
import com.proyecto_final.ms_account.domain.exception.AccountLimitExceededException;
import com.proyecto_final.ms_account.domain.model.Account;
import com.proyecto_final.ms_account.domain.model.CustomerTypeEnum;
import io.reactivex.rxjava3.core.Single;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.Clock;
import java.time.Instant;

/**
 * Implementa el caso de uso CreateAccountUseCase de la cuenta: orquesta las reglas del negocio.
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class CreateAccountService implements CreateAccountUseCase {

    private final AccountRepositoryPort accountRepositoryPort;

    private final CustomerClientPort customerClientPort;

    private final AccountRulesValidator accountRulesValidator;

    private final Clock clock;

    /**
     * {@inheritDoc}
     */
    @Override
    public Single<Account> create(CreateAccountDto createAccountDto) {
        return Single.defer(() -> {
                    accountRulesValidator.validateConditions(
                            createAccountDto.type(),
                            createAccountDto.monthlyMovementLimit(),
                            createAccountDto.maintenanceFee(),
                            createAccountDto.movementDayOfMonth());
                    return customerClientPort.findCustomerById(createAccountDto.customerId());
                })
                // flatMap: con el cliente ya consultado en ms-customer, se encadena el resto de la creación
                .flatMap(customer -> createForCustomer(createAccountDto, customer));
    }

    /**
     * Valida las reglas que dependen del tipo de cliente y cuenta las cuentas que ya tiene.
     */
    private Single<Account> createForCustomer(CreateAccountDto createAccountDto, CustomerInfoDto customer) {
        accountRulesValidator.validateAccountTypeAllowed(createAccountDto.type(), customer.type());
        accountRulesValidator.validateParticipants(
                customer.type(),
                createAccountDto.holders(),
                createAccountDto.signers()
        );

        return accountRepositoryPort
                .countActiveByCustomerIdAndType(createAccountDto.customerId(), createAccountDto.type())
                .flatMap(existingAccounts ->
                        saveIfLimitNotReached(createAccountDto, customer.type(), existingAccounts));
    }

    /**
     * Guarda la cuenta nueva, o falla si el cliente ya alcanzó el máximo de cuentas de ese tipo.
     */
    private Single<Account> saveIfLimitNotReached(
            CreateAccountDto createAccountDto, CustomerTypeEnum customerType, long existingAccounts) {
        if (createAccountDto.type().isLimitReached(customerType, existingAccounts)) {
            log.warn("Account rejected, customer {} reached the limit of {} accounts",
                    createAccountDto.customerId(), createAccountDto.type());
            return Single.error(new AccountLimitExceededException(
                    createAccountDto.customerId(), createAccountDto.type()));
        }

        return accountRepositoryPort.save(buildNewAccount(createAccountDto, customerType));
    }

    /**
     * Construye la cuenta nueva: activa y con las fechas de creación y actualización iguales.
     */
    private Account buildNewAccount(CreateAccountDto createAccountDto, CustomerTypeEnum customerType) {
        Instant now = clock.instant();
        return Account.builder()
                .customerId(createAccountDto.customerId())
                .customerType(customerType)
                .type(createAccountDto.type())
                .monthlyMovementLimit(createAccountDto.monthlyMovementLimit())
                .maintenanceFee(createAccountDto.maintenanceFee())
                .movementDayOfMonth(createAccountDto.movementDayOfMonth())
                .holders(createAccountDto.holders())
                .signers(createAccountDto.signers())
                .isActive(true)
                .createdAt(now)
                .updatedAt(now)
                .build();
    }
}
