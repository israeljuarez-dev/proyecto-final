package com.proyecto_final.ms_customer.application.service;
import com.proyecto_final.ms_customer.application.port.in.dto.UpdateCustomerDto;
import com.proyecto_final.ms_customer.application.port.in.use_cases.UpdateCustomerUseCase;
import com.proyecto_final.ms_customer.application.port.out.CustomerRepositoryPort;
import com.proyecto_final.ms_customer.domain.exception.CustomerNotFoundException;
import com.proyecto_final.ms_customer.domain.model.Customer;
import io.reactivex.rxjava3.core.Single;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.Clock;

/**
 * Implementa el caso de uso UpdateCustomerUseCase del cliente: orquesta las reglas del negocio.
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class UpdateCustomerService implements UpdateCustomerUseCase {

    private final CustomerRepositoryPort customerRepositoryPort;

    private final Clock clock;

    /**
     * {@inheritDoc}
     */
    @Override
    public Single<Customer> update(UpdateCustomerDto updateCustomerDto) {
        return customerRepositoryPort.findActiveById(updateCustomerDto.customerId())
                .switchIfEmpty(Single.defer(() -> {
                    log.warn("Customer not found, id {}", updateCustomerDto.customerId());
                    return Single.error(new CustomerNotFoundException(updateCustomerDto.customerId()));
                }))
                .map(customer -> customer.update(
                        updateCustomerDto.legalName(),
                        updateCustomerDto.email(),
                        updateCustomerDto.phoneNumber(),
                        clock.instant()
                ))
                .flatMap(customerRepositoryPort::save);
    }
}
