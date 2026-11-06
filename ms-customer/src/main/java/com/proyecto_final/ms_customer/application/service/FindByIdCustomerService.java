package com.proyecto_final.ms_customer.application.service;

import com.proyecto_final.ms_customer.application.port.in.use_cases.FindCustomerByIdUseCase;
import com.proyecto_final.ms_customer.application.port.out.CustomerRepositoryPort;
import com.proyecto_final.ms_customer.domain.exception.CustomerNotFoundException;
import com.proyecto_final.ms_customer.domain.model.Customer;
import io.reactivex.rxjava3.core.Single;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * Implementa el caso de uso FindByIdCustomerUseCase del cliente: orquesta las reglas del negocio.
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class FindByIdCustomerService implements FindCustomerByIdUseCase {

    private final CustomerRepositoryPort customerRepositoryPort;

    /**
     * {@inheritDoc}
     */
    @Override
    public Single<Customer> findById(String customerId) {
        return customerRepositoryPort.findActiveById(customerId)
                .switchIfEmpty(Single.defer(() -> {
                    log.warn("Customer not found, id {}", customerId);
                    return Single.error(new CustomerNotFoundException(customerId));
                }));
    }
}
