package com.proyecto_final.ms_customer.application.service;

import com.proyecto_final.ms_customer.application.port.in.dto.CreateCustomerDto;
import com.proyecto_final.ms_customer.application.port.in.use_cases.CreateCustomerUseCase;
import com.proyecto_final.ms_customer.application.port.out.CustomerRepositoryPort;
import com.proyecto_final.ms_customer.domain.exception.DuplicateCustomerException;
import com.proyecto_final.ms_customer.domain.exception.InvalidDocumentTypeException;
import com.proyecto_final.ms_customer.domain.model.Customer;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Single;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.Clock;
import java.time.Instant;

/**
 * Implementa el caso de uso CreateCustomerUseCase del cliente: orquesta las reglas del negocio.
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class CreateCustomerService implements CreateCustomerUseCase {

    private final CustomerRepositoryPort customerRepositoryPort;

    private final Clock clock;

    /**
     * {@inheritDoc}
     */
    @Override
    public Single<Customer> create(CreateCustomerDto createCustomerDto) {
        return Single.defer(() -> {
                    validateDocumentType(createCustomerDto);
                    return customerRepositoryPort.existsActiveByDocument(
                            createCustomerDto.documentType(),
                            createCustomerDto.documentNumber()
                    );
                })
                .flatMap(isDuplicated ->
                        isDuplicated(createCustomerDto, isDuplicated)
                                .andThen(Single.defer(() -> {
                                    Instant now = clock.instant();

                                    Customer customerSaved = Customer.builder()
                                            .type(createCustomerDto.type())
                                            .documentType(createCustomerDto.documentType())
                                            .documentNumber(createCustomerDto.documentNumber())
                                            .legalName(createCustomerDto.legalName())
                                            .email(createCustomerDto.email())
                                            .phoneNumber(createCustomerDto.phoneNumber())
                                            .isActive(true)
                                            .createdAt(now)
                                            .updatedAt(now)
                                            .build();

                                    return customerRepositoryPort.save(customerSaved);
                                }))
                );
    }

    /**
     * Verifica que el tipo de documento sea admitido por el tipo de cliente.
     */
    private void validateDocumentType(CreateCustomerDto createCustomerDto) {
        if (!createCustomerDto.type().allows(createCustomerDto.documentType())) {
            log.warn("Customer creation rejected, document type {} not allowed for customer type {}",
                    createCustomerDto.documentType(), createCustomerDto.type());
            throw new InvalidDocumentTypeException(createCustomerDto.type(), createCustomerDto.documentType());
        }
    }

    /**
     * Valida si el cliente ya existe mediante su tipo y número de documento.
     * Emite un error si encuentra un cliente activo duplicado; de lo contrario, completa correctamente.
     */
    private Completable isDuplicated(CreateCustomerDto createCustomerDto, boolean isDuplicated) {
        if (isDuplicated) {
            log.warn("Customer creation rejected, duplicated document of type {}", createCustomerDto.documentType());
            return Completable.error(
                    new DuplicateCustomerException(
                            createCustomerDto.documentType(),
                            createCustomerDto.documentNumber()
                    )
            );
        }

        return Completable.complete();
    }
}
