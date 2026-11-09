package com.proyecto_final.ms_credit.application.service;

import com.proyecto_final.ms_credit.application.port.in.dto.CreateCreditDto;
import com.proyecto_final.ms_credit.application.port.in.use_cases.CreateCreditUseCase;
import com.proyecto_final.ms_credit.application.port.out.CreditRepositoryPort;
import com.proyecto_final.ms_credit.application.port.out.client.customer.CustomerClientPort;
import com.proyecto_final.ms_credit.application.port.out.dto.CustomerInfoDto;
import com.proyecto_final.ms_credit.application.validator.CreditRulesValidator;
import com.proyecto_final.ms_credit.domain.exception.MaxCreditsExceededException;
import com.proyecto_final.ms_credit.domain.model.Credit;
import com.proyecto_final.ms_credit.domain.model.CustomerTypeEnum;
import io.reactivex.rxjava3.core.Single;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.Clock;
import java.time.Instant;

/**
 * Implementa el caso de uso CreateCreditUseCase del crédito: orquesta las reglas del negocio.
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class CreateCreditService implements CreateCreditUseCase {

    private final CreditRepositoryPort creditRepositoryPort;

    private final CustomerClientPort customerClientPort;

    private final CreditRulesValidator creditRulesValidator;

    private final Clock clock;

    /**
     * {@inheritDoc}
     */
    @Override
    public Single<Credit> create(CreateCreditDto createCreditDto) {
        return Single.defer(() -> {
                    creditRulesValidator.validateConditions(
                            createCreditDto.type(),
                            createCreditDto.approvedAmount(),
                            createCreditDto.creditLimit());
                    return customerClientPort.findCustomerById(createCreditDto.customerId());
                })
                .flatMap(customer -> createForCustomer(createCreditDto, customer));
    }

    /**
     * Cuenta los créditos del mismo tipo que ya tiene el cliente y decide si se puede crear otro.
     */
    private Single<Credit> createForCustomer(CreateCreditDto createCreditDto, CustomerInfoDto customer) {
        return creditRepositoryPort
                .countActiveByCustomerIdAndType(createCreditDto.customerId(), createCreditDto.type())
                .flatMap(existingCredits ->
                        saveIfLimitNotReached(createCreditDto, customer.type(), existingCredits));
    }

    /**
     * Guarda el crédito nuevo, o falla si el cliente ya alcanzó el máximo de créditos de ese tipo.
     */
    private Single<Credit> saveIfLimitNotReached(
            CreateCreditDto createCreditDto, CustomerTypeEnum customerType, long existingCredits) {
        if (createCreditDto.type().isLimitReached(customerType, existingCredits)) {
            log.warn("Credit rejected, customer {} reached the limit of {} credits",
                    createCreditDto.customerId(), createCreditDto.type());
            return Single.error(new MaxCreditsExceededException(
                    createCreditDto.customerId(), createCreditDto.type()));
        }

        return creditRepositoryPort.save(buildNewCredit(createCreditDto, customerType));
    }

    /**
     * Construye el crédito nuevo: activo y con las fechas de creación y actualización iguales.
     */
    private Credit buildNewCredit(CreateCreditDto createCreditDto, CustomerTypeEnum customerType) {
        Instant now = clock.instant();
        return Credit.builder()
                .customerId(createCreditDto.customerId())
                .customerType(customerType)
                .type(createCreditDto.type())
                .approvedAmount(createCreditDto.approvedAmount())
                .creditLimit(createCreditDto.creditLimit())
                .isActive(true)
                .createdAt(now)
                .updatedAt(now)
                .build();
    }
}
